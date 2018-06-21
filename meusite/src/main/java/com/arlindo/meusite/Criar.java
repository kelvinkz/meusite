package com.arlindo.meusite;

import java.sql.Connection;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.arlindo.contato.Contato;
import com.arlindo.contato.ContatoDAO;

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
		TextField<String> inputEmail = new TextField<String>("email");
		TextField<String> inputTelefone = new TextField<String>("telefone");
		form.add(inputEmail, inputNome, inputTelefone);
	}

	private void salvar(Contato contatoSubmetido) {
		Connection conexao = ((WicketApplication) getApplication()).getConexao();
		ContatoDAO contatoDAO = new ContatoDAO(conexao);
		contatoDAO.inserir(contatoSubmetido);
	}
}
