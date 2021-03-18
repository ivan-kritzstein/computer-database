package com.excilys.formation.Dto;

public class CompanyDto {

		private String name;
		private String id;
		
		public CompanyDto() {
			
		}
		
		public CompanyDto(String id, String name) {
			this.setId(id);
			this.setName(name);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "CompanyDto [name=" + name + ", id=" + id + "]";
		}
}
