<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
	<p>Consulta de transferências</p>
	<div class="form-fields">
		<sf:form modelAttribute="pesquisaModel" action="/realizarPesquisaTransferencia" method="POST">
			<table>
				<tr>
					<td class="label" class="label"><label for="contaOrigem">Conta Origem:</label></td>
					<td>
						<sf:input path="contaOrigem" cssClass="conta" /><br/>
					</td>
					<td class="label"><label for="contaDestino">Conta Destino:</label></td>
					<td>
						<sf:input path="contaDestino" cssClass="conta" /><br/>
					</td>
				</tr>
				<tr>
					<td class="label"><label for="dataTransferencia">Data da Transferência:</label></td>
					<td>
						<sf:input path="dataTransferencia" cssClass="data" /><br/>
					</td>
					<td class="label"><label for="dataCadastro">Data de Cadastro:</label></td>
					<td>
						<sf:input path="dataCadastro" cssClass="data" /><br/>
					</td>
				</tr>
				<tr>
					<td class="label"><label for="tipo">Tipo:</label></td>
					<td>
						<sf:select path="tipo" items="${tiposTransferencia}" /><br/>
					</td>
					<td class="label"><label for="valor">Valor:</label></td>
					<td>
						<sf:input path="valor" cssClass="money" /><br/>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="buttons">
						<input type="submit" value="Pesquisar" />
						<a href="/cadastroTransferencia"><input type="button" value="Cadastrar" /></a>
					</td>
				</tr>
			</table>
		</sf:form>
	</div>
	<c:if test="${not empty transferencias}">
		<p>Resultados da pesquisa:</p>
		<div class="search-results">
			<table cellspacing="0">
				<thead>
					<tr>
						<th>Data de Cadastro</th>
						<th>Data da Transferência</th>
						<th>Conta de Origem</th>
						<th>Conta de Destino</th>
						<th>Tipo</th>
						<th>Valor</th>
						<th>Taxa</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${transferencias}" var="transf">
						<tr>
							<td><joda:format pattern="dd/MM/yyyy" value="${transf.dataCadastro}" /></td>
							<td><joda:format pattern="dd/MM/yyyy" value="${transf.dataTransferencia}" /></td>
							<td>${transf.contaOrigem}</td>
							<td>${transf.contaDestino}</td>
							<td>${transf.tipo}</td>
							<td><fmt:formatNumber minFractionDigits="2" type="currency" value="${transf.valor}" /></td>
							<td><fmt:formatNumber minFractionDigits="2" type="currency" value="${transf.taxa}" /></td>
							<td>
								<a href="/excluirTransferencia/?id=${transf.id}" title="Excluir">
									<img src="/imgs/deleteBtn.png"/>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
</div>
