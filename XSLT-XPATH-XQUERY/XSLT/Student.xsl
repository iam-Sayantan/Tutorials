<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				College name:
				<b>
					<xsl:value-of select="StudentScores/College"></xsl:value-of>
				</b>
				<table border="1">
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Score</th>
						<th>Grade</th>
					</tr>
					<xsl:for-each select="StudentScores/Student">
						<xsl:sort select="@id" data-type="number" order="descending"/>
						<tr>
							<td>
								<xsl:value-of select="FirstName" />
							</td>
							<td>
								<xsl:value-of select="LastName" />
							</td>
							<td>
								<xsl:value-of select="Score" />
							</td>
							<td>
								<xsl:choose>
									<xsl:when test="Score>=90">
										<b>Grade A</b>
									</xsl:when>
									<xsl:when test="Score>=80">
										<b>Grade B</b>
									</xsl:when>
									<xsl:when test="Score>=70">
										<b>Grade C</b>
									</xsl:when>
									<xsl:otherwise>
										<b>Grade D</b>
									</xsl:otherwise>
								</xsl:choose>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>