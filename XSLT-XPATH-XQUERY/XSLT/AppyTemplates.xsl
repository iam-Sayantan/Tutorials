<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<b>Order Id:</b>
				<xsl:value-of select="/Order/@id" />
				<xsl:apply-templates/>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="CustomerName">
		<br/><b>Customer Name: </b>
		<xsl:value-of select="." />
	</xsl:template>
	<xsl:template match="Item">
		<br/><br/>  <b>Item Name: </b>
		<xsl:value-of select="ItemName" /><br/>
		<b>Item Price: </b>
		<xsl:value-of select="Price" />
	</xsl:template>
</xsl:stylesheet>