<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">  <!-- Root Element -->
	<xsl:template match="/"> <!-- Child Element; slash is for navigating the directories -->

		<html>
			<body>
				<h2>Order Details</h2>
				<h3><xsl:value-of select="Order/CustomerName"/></h3> <!-- To print a value of a particular HTML element,
																		we can use the xsl:value-of, XSL style sheet element. 
																		And in the select expression we need to provide a 
																		XPATH which is the path of the element or attribute
																		in the XML document.  -->
				<h3><xsl:value-of select="Order/@id"/></h3> <!-- So the id is the attribute on the Order element and the 
																XPATH expression to retrieve ids or to retrieve 
																attributes is @ symbol. use the @ symbol to get the attribute 
																values on a XML document into your XSL output. -->
																
				<table border="1">
					<tr bgcolor="blue">
						<th>Item Id</th>
						<th>Item Name</th>
						<th>Item Price</th>
						<th>Item Quantity</th>
					</tr>
					<xsl:for-each select="Order/Item">
						<tr>
							<td><xsl:value-of select="ItemId"/></td>
							<td><xsl:value-of select="ItemName"/></td>
							<td><xsl:value-of select="Price"/></td>
							<td><xsl:value-of select="Quantity"/></td>
						</tr>
					</xsl:for-each>
				
				</table>
			</body>		
		</html>

	</xsl:template>
</xsl:stylesheet>