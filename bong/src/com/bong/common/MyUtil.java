package com.bong.common;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.stereotype.Service;

@Service("myUtil")
public class MyUtil {
    //********************************************
	// �������� �� ���ϱ�
	public int pageCount(int numPerPage, int dataCount) {
		int pageCount=0;
		
		if(dataCount > 0) {
			if(dataCount % numPerPage == 0)
				pageCount=dataCount/numPerPage;
			else
				pageCount=dataCount/numPerPage+1;
		}
		
		return pageCount;
	}
	
    //********************************************
	// ����¡(paging) ó��(GET ���) : ��Ʈ��Ʈ��
	public String paging(int current_page, int total_page, String list_url) {
		StringBuffer sb=new StringBuffer();
		
		int numPerBlock=10;
		int currentPageSetup;
		int n, page;
		
		if(current_page<1 || total_page < 1)
			return "";
		
		if(list_url.indexOf("?")!=-1)
			list_url+="&";
		else
			list_url+="?";
		
		// currentPageSetup : ǥ����ù������-1
		currentPageSetup=(current_page/numPerBlock)*numPerBlock;
		if(current_page%numPerBlock==0)
			currentPageSetup=currentPageSetup-numPerBlock;

		sb.append("<ul class='pagination pagination-sm'>");
		// ó��������
		if(current_page > 1) {
			sb.append("<li><a href='"+list_url+"page=1' aria-label='First'><span aria-hidden='true' class='glyphicon glyphicon-step-backward'></span></a></li>");
		} else {
			sb.append("<li class='disabled'><a href='#' aria-label='First'><span aria-hidden='true' class='glyphicon glyphicon-step-backward'></span></a></li>");
		}
		// ����(10������ ��)
		n=current_page-numPerBlock;
		if(total_page > numPerBlock && currentPageSetup > 0) {
			sb.append("<li><a href='"+list_url+"page="+n+"' aria-label='Previous'><span aria-hidden='true' class='glyphicon glyphicon-triangle-left'></span></a></li>");
		} else {
			sb.append("<li class='disabled'><a href='#' aria-label='Previous'><span aria-hidden='true' class='glyphicon glyphicon-triangle-left'></span></a></li>");
		}
		
		// �ٷΰ���
		page=currentPageSetup+1;
		while(page<=total_page && page <=(currentPageSetup+numPerBlock)) {
			if(page==current_page) {
				sb.append("<li class='active'><a href='#'>"+page+" <span class='sr-only'>(current)</span></a></li>");
			} else {
				sb.append("<li><a href='"+list_url+"page="+page+"'>"+page+"</a></li>");
			}
			page++;
		}
		
		// ����(10������ ��)
		n=current_page+numPerBlock;
		if(n>total_page) n=total_page;
		if(total_page-currentPageSetup>numPerBlock) {
			sb.append("<li><a href='"+list_url+"page="+n+"' aria-label='Next'><span aria-hidden='true' class='glyphicon glyphicon-triangle-right'></span></a></li>");
		} else {
			sb.append("<li class='disabled'><a href='#' aria-label='Next'><span aria-hidden='true' class='glyphicon glyphicon-triangle-right'></span></a></li>");
		}
		// ������������
		if(current_page<total_page) {
			sb.append("<li><a href='"+list_url+"page="+total_page+"' aria-label='Last'><span aria-hidden='true' class='glyphicon glyphicon-step-forward'></span></a></li>");
		} else {
			sb.append("<li class='disabled'><a href='#' aria-label='Last'><span aria-hidden='true' class='glyphicon glyphicon-step-forward'></span></a></li>");
		}
		
		sb.append("</ul>");
	
		return sb.toString();
	}

    //********************************************
	// javascript ������ ó��(javascript listPage() �Լ� ȣ��) : ��Ʈ��Ʈ��
    public String paging(int current_page, int total_page) {
		if(current_page < 1 || total_page < 1)
			return "";

        int numPerBlock = 10;   // ����Ʈ�� ��Ÿ�� ������ ��
        int currentPageSetup;
        int n;
        int page;
        StringBuffer sb=new StringBuffer();
        
        // ǥ���� ù ������
        currentPageSetup = (current_page / numPerBlock) * numPerBlock;
        if (current_page % numPerBlock == 0)
        	currentPageSetup = currentPageSetup - numPerBlock;

		sb.append("<ul class='pagination pagination-sm'>");
        
        // ó��������
		if(current_page > 1) {
			sb.append("<li><a onclick='listPage(1);' aria-label='First'><span aria-hidden='true' class='glyphicon glyphicon-step-backward'></span></a></li>");
		} else {
			sb.append("<li class='disabled'><a href='#' aria-label='First'><span aria-hidden='true' class='glyphicon glyphicon-step-backward'></span></a></li>");
		}
        // ����(10������ ��)
        n = current_page - numPerBlock;
		if(total_page > numPerBlock && currentPageSetup > 0) {
			sb.append("<li><a onclick='listPage("+n+");' aria-label='Previous'><span aria-hidden='true' class='glyphicon glyphicon-triangle-left'></span></a></li>");
		} else {
			sb.append("<li class='disabled'><a href='#' aria-label='Previous'><span aria-hidden='true' class='glyphicon glyphicon-triangle-left'></span></a></li>");
		}

        // �ٷΰ��� ������ ����
		page=currentPageSetup+1;
		while(page<=total_page && page <=(currentPageSetup+numPerBlock)) {
			if(page==current_page) {
				sb.append("<li class='active'><a href='#'>"+page+" <span class='sr-only'>(current)</span></a></li>");
			} else {
				sb.append("<li><a onclick='listPage("+page+");'>"+page+"</a></li>");
			}
			page++;
		}
        
        // ����(10������ ��)
        n = current_page + numPerBlock;
		if(n>total_page) n=total_page;
		if(total_page-currentPageSetup>numPerBlock) {
			sb.append("<li><a onclick='listPage("+n+"); aria-label='Next'><span aria-hidden='true' class='glyphicon glyphicon-triangle-right'></span></a></li>");
		} else {
			sb.append("<li class='disabled'><a href='#' aria-label='Next'><span aria-hidden='true' class='glyphicon glyphicon-triangle-right'></span></a></li>");
		}
        // ������ ������
		if(current_page<total_page) {
			sb.append("<li><a onclick='listPage("+total_page+");' aria-label='Last'><span aria-hidden='true' class='glyphicon glyphicon-step-forward'></span></a></li>");
		} else {
			sb.append("<li class='disabled'><a href='#' aria-label='Last'><span aria-hidden='true' class='glyphicon glyphicon-step-forward'></span></a></li>");
		}
        
        sb.append("</ul>");

        return sb.toString();
    }

    //********************************************
    // Ư�����ڸ� HTML ���ڷ� ����
	public String escape(String str) {
		if(str==null||str.length()==0)
			return "";
		
		StringBuilder builder = new StringBuilder((int)(str.length() * 1.2f));

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {
			case '<':
				builder.append("&lt;");
				break;
			case '>':
				builder.append("&gt;");
				break;
			case '&':
				builder.append("&amp;");
				break;
			case '\"':
				builder.append("&quot;");
				break;
			default:
				builder.append(c);
			}
		}
		return builder.toString();
	}

    //********************************************
    // Ư�����ڸ� HTML ���ڷ� ���� �� ���͸� <br>�� ���� 
     public String htmlSymbols(String str) {
		if(str==null||str.length()==0)
			return "";

    	 str=str.replaceAll("&", "&amp;");
    	 str=str.replaceAll("\"", "&quot;");
    	 str=str.replaceAll(">", "&gt;");
    	 str=str.replaceAll("<", "&lt;");
    	 
    	 str=str.replaceAll(" ", "&nbsp;");
    	 str=str.replaceAll("\n", "<br>");
    	 
    	 return str;
     }

    //********************************************
 	// ���ڿ��� ������ ���ϴ� ���ڿ��� �ٸ� ���ڿ��� ��ȯ
 	// String str = replaceAll(str, "\n", "<br>"); // ���͸� <br>�� ��ȯ
 	public String replaceAll(String str, String oldStr, String newStr) throws Exception {
 		if(str == null)
 			return "";

         Pattern p = Pattern.compile(oldStr);
         
         // �Է� ���ڿ��� �Բ� ���� Ŭ���� ����
         Matcher m = p.matcher(str);

         StringBuffer sb = new StringBuffer();
         // ���ϰ� ��ġ�ϴ� ���ڿ��� newStr �� ��ü�ذ��� ���ο� ���ڿ��� �����.
         while(m.find()) {
             m.appendReplacement(sb, newStr);
         }

         // ������ �κ��� ���ο� ���ڿ� ���� �� ���δ�.
         m.appendTail(sb);

 		return sb.toString();
 	}

    //********************************************
 	// E-Mail �˻�
     public boolean isValidEmail(String email) {
         if (email==null) return false;
         boolean b = Pattern.matches(
        	 "[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", 
             email.trim());
         return b;
     }

    //********************************************
 	// NULL �� ��� ""�� 
     public String checkNull(String str) {
         String strTmp;
         if (str == null)
             strTmp = "";
         else
             strTmp = str;
         return strTmp;
     }
}
