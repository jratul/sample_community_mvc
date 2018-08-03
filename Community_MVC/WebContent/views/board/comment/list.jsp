<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
{
	"startPageNum" : ${startPageNum},
	"endPageNum" : ${endPageNum},
	"list" : [
	<c:forEach var="tmp" items="${list }" varStatus="index">
		{
			"num" : ${tmp.num },
			"writer" : "${tmp.writer }",
			"content" : "${tmp.content }",
			"postNum" : ${tmp.postNum },
			"parentNum" : ${tmp.parentNum },
			"depth" : ${tmp.depth},
			"regdate" : "${tmp.regdate }",
			"likeCnt" : ${tmp.likeCnt },
			"dislikeCnt" : ${tmp.dislikeCnt },
			"pic" : "${tmp.pic}",
			"isDelete" : ${tmp.isDelete}
		}
		<c:if test="${!index.last }">,</c:if>
	</c:forEach>
	]
}
