package br.sptrans.transportepublico.modelo;

import java.util.Date;
import java.util.List;


public class NoticiaModelo {
    private String id;
    private String text;
    private Date created_at;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	public class NoticiaModeloRoot
	{
		private List<NoticiaModelo> messages;

		public List<NoticiaModelo> getMessage() {
			return messages;
		}

		public void setMessage(List<NoticiaModelo> message) {
			this.messages = message;
		}
	}
}
