<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<p>Consulta de transferências</p>
	<div class="form-fields">
		<sf:form modelAttribute="pesquisaModel" action="realizarPesquisaTransferencia" method="POST">
			<table>
				<tr>
					<td class="label" class="label"><label for="contaOrigem">Conta Origem:</label></td>
					<td>
						<sf:input path="contaOrigem" /><br/>
						<sf:errors path="contaOrigem" cssClass="error"/>
					</td>
					<td class="label"><label for="contaDestino">Conta Destino:</label></td>
					<td>
						<sf:input path="contaDestino" /><br/>
						<sf:errors path="contaDestino" cssClass="error"/>
					</td>
				</tr>
				<tr>
					<td class="label"><label for="dataTransferencia">Data da Transferência:</label></td>
					<td>
						<sf:input path="dataTransferencia" /><br/>
						<sf:errors path="dataTransferencia" cssClass="error"/>
					</td>
					<td class="label"><label for="dataCadastro">Data de Cadastro:</label></td>
					<td>
						<sf:input path="dataCadastro" /><br/>
						<sf:errors path="dataCadastro" cssClass="error"/>
					</td>
				</tr>
				<tr>
					<td class="label"><label for="tipo">Tipo:</label></td>
					<td>
						<sf:input path="tipo" /><br/>
						<sf:errors path="tipo" cssClass="error"/>
					</td>
					<td class="label"><label for="valor">Valor:</label></td>
					<td>
						<sf:input path="valor" /><br/>
						<sf:errors path="valor" cssClass="error"/>
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
							<td>${transf.dataCadastro}<td>
							<td>${transf.dataTransferencia}<td>
							<td>${transf.contaOrigem}<td>
							<td>${transf.contaDestino}<td>
							<td>${transf.tipo}<td>
							<td>${transf.valor}<td>
							<td>${transf.taxa}<td>
							<td>
								<a href="/editarTransferencia/id=${transferencia.id}" title="Editar">
									<img src="/imgs/editBtn.png"/>
								</a>
								<a href="/excluirTransferencia/id=${transferencia.id}" title="Excluir">
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