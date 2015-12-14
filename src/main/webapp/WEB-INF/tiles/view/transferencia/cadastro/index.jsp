<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div>
	<p>Cadastro de Transferência</p>
	<div class="form-fields">
		<sf:form modelAttribute="transferencia" action="realizarCadastroTransferencia" method="POST">
			<table>
				<tr>
					<td class="label">Data de Cadastro:</label></td>
					<td>
						<sf:input path="dataCadastro" disabled="true" />
					</td>
					<td class="label"><label for="dataTransferencia">Data da Transferência:</label></td>
					<td>
						<sf:input path="dataTransferencia" /><br/>
						<sf:errors path="dataTransferencia" cssClass="error subtext"/>
					</td>
				</tr>
				<tr>
					<td class="label" class="label"><label for="contaOrigem">Conta Origem:</label></td>
					<td>
						<sf:input path="contaOrigem" /><br/>
						<sf:errors path="contaOrigem" cssClass="error subtext"/>
					</td>
					<td class="label"><label for="contaDestino">Conta Destino:</label></td>
					<td>
						<sf:input path="contaDestino" /><br/>
						<sf:errors path="contaDestino" cssClass="error subtext"/>
					</td>
				</tr>
				<tr>
					<td class="label"><label for="tipo">Tipo:</label></td>
					<td>
						<sf:input path="tipo" /><br/>
						<sf:errors path="tipo" cssClass="error subtext"/>
					</td>
					<td class="label"><label for="valor">Valor:</label></td>
					<td>
						<sf:input path="valor" /><br/>
						<sf:errors path="valor" cssClass="error subtext"/>
					</td>
				</tr>
				<tr>
					<td class="label"><label for="taxa">Taxa:</label></td>
					<td>
						<sf:input path="taxa" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="buttons">
						<input type="submit" value="Cadastrar" />
						<a href="/transferencias"><input type="button" value="Voltar" /></a>
					</td>
				</tr>
			</table>
		</sf:form>
	</div>
</div>
