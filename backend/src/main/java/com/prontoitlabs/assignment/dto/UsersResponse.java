package com.prontoitlabs.assignment.dto;

import java.util.List;

public class UsersResponse {

	private String errorMessage;
	private boolean status;
	private Data data;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Data getData() {
		return data;
	}
	
	public void setData(Data data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		String details = "{'errorMessage':" + errorMessage + ",'status':" + status + ",'data':" + data + "}";
		return details;
	}
	
	public class Data {
		
		private int currentPage;
		private int currentPageSize;
		private int totalPages;
		private int totalElements;
		private List<User> content;
		
		public Data() {}
		
		public Data(int currentPage, int currentPageSize, int totalPages, int totalElements, List<User> content) {
			this.currentPage = currentPage;
			this.currentPageSize = currentPageSize;
			this.totalPages = totalPages;
			this.totalElements = totalElements;
			this.content = content;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public int getCurrentPageSize() {
			return currentPageSize;
		}

		public void setCurrentPageSize(int currentPageSize) {
			this.currentPageSize = currentPageSize;
		}

		public int getTotalPages() {
			return totalPages;
		}

		public void setTotalPages(int totalPages) {
			this.totalPages = totalPages;
		}

		public int getTotalElements() {
			return totalElements;
		}

		public void setTotalElements(int totalElements) {
			this.totalElements = totalElements;
		}

		public List<User> getContent() {
			return content;
		}

		public void setContent(List<User> content) {
			this.content = content;
		}
		
		@Override
		public String toString() {
			
			StringBuffer details = new StringBuffer("{'currentPage':");
			details.append(this.currentPage);
			details.append(",'currentPageSize':");
			details.append(this.currentPageSize);
			details.append(",'totalPages':");
			details.append(this.totalPages);
			details.append(",'totalElements':");
			details.append(this.totalElements);
			details.append(",'content':[");
			
			for (int i = 0; i < content.size(); i++) {
				
				details.append(content.get(i).toString());
				
				if (i != content.size()-1) {
					details.append(",");
				}
			}
			
			details.append("]}");
			
			return details.toString();
		}
	}
}
