<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">

		<html>
			<body>
				<h3>
					Order Information:
					<xsl:apply-templates />
					<br />
					<br />
					Total number of items in the order
					<b>
						<xsl:value-of select="count(/Order/Item)" />
					</b>
					<br />
					<b>
						Total price of the order:
						<xsl:value-of select="sum(/Order/Item/Price)"></xsl:value-of>
					</b>
				</h3>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="CustomerName">
		<b>
			<br></br>
			Customer Name:
			<xsl:value-of select="." />
		</b>
	</xsl:template>
	<xsl:template match="Item">
		<br />
		Order Id:
		<xsl:value-of select="../@id"></xsl:value-of>
		<br />
		Item:
		<xsl:value-of select="translate(./ItemName,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')"></xsl:value-of>
		<br />
		Price:
		<xsl:value-of select="round(./Price)"></xsl:value-of>
		<xsl:if test="Price &gt; 100">
			(Eligible for free shipping)
		</xsl:if>
		<xsl:if test="Price &lt; 100">
			(Should be purchased with an item whose price is greater than 100 for
			free shipping)
		</xsl:if>


	</xsl:template>
</xsl:stylesheet>