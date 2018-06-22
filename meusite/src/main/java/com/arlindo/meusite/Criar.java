package com.arlindo.meusite;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.arlindo.contato.Contato;
import com.arlindo.contato.ContatoDAO;
import com.arlindo.contato.EstadoCivil;

public class Criar extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7851079717099564031L;

	public Criar() {

		Contato contato = new Contato();
		CompoundPropertyModel<Contato> compoundPropertyModel = new CompoundPropertyModel<Contato>(contato);
		Form<Contato> form = new Form<Contato>("formularioContato", compoundPropertyModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				Contato contatoSubmetido = getModelObject();
				System.out.println("Contato a inserir: " + contatoSubmetido.toString());
				salvar(contatoSubmetido);
				setResponsePage(Inicio.class);
			}
		};
		add(form);

		TextField<String> inputNome = new TextField<String>("nome");
		inputNome.setLabel(Model.of("Nome do contato")).setRequired(true).add(StringValidator.maximumLength(50));

		TextField<String> inputEmail = new TextField<String>("email");
		inputEmail.setLabel(Model.of("Email do contato")).add(EmailAddressValidator.getInstance());

		TextField<String> inputTelefone = new TextField<String>("telefone");

		DropDownChoice<EstadoCivil> comboEstadoCivil = new DropDownChoice<EstadoCivil>("estadoCivil",
				Arrays.asList(EstadoCivil.values()), new IChoiceRenderer<EstadoCivil>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Object getDisplayValue(EstadoCivil object) {
						return object.getLabel();
					}

					@Override
					public String getIdValue(EstadoCivil object, int index) {
						return object.name();
					}

					@Override
					public EstadoCivil getObject(String id, IModel<? extends List<? extends EstadoCivil>> choices) {
						// TODO Auto-generated method stub
						return null;
					}
				});
		form.add(inputEmail, inputNome, inputTelefone, comboEstadoCivil);

		add(new FeedbackPanel("feedbackMessage", new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));
	}

	private void salvar(Contato contatoSubmetido) {
		Connection conexao = ((WicketApplication) getApplication()).getConexao();
		ContatoDAO contatoDAO = new ContatoDAO(conexao);
		contatoDAO.inserir(contatoSubmetido);
	}
}
