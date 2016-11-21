package br.com.fieweb.managedbean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fieweb.model.Login;

@ManagedBean(name="loginBean") // para o bean do formulário
@SessionScoped       // para trabalhar com sessoes dos usuarios
public class LoginBean implements Serializable {
	
	private Login login;
	
	public LoginBean(){
		login = new Login();
	}
	
	
	public String logarNoSistema(){
		if(login.getLogin().equals("Admin")  && login.getSenha().equals("Admin") ){
			HttpSession  session  = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("Usuario", login);
			return "/restrito/index.jsf?faces-redirect=true";
			
		}else{
			return "/publico/login.jsf?faces-redirect=true";
		}
	}
	
	public String sairDoSistema(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/publico/login.jsf?faces-redirect=true";
	}
	

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	

}
