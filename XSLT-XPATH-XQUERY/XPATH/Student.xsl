<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				College Name:
				<b><xsl:value-of select="/StudentScores/College"/></b>
				<br/>
				Student Information:
				<br/>
				<xsl:apply-templates
					select="/StudentScores/Student[@id=4]" />
			</body>
		</html>
	</xsl:template>
	<xsl:template match="Student">
		<b>Student Name:</b>
		<br />
		First Name:
		<b><xsl:value-of select="FirstName" /></b>
		<br />
		Last Name:
		<b><xsl:value-of select="LastName" /></b>
		<br />
		Score:
		<b><xsl:value-of select="Score" /></b>
	</xsl:template>
</xsl:stylesheet>