<ul>
	{
		for $x in doc("Books.xml")/books/book/title
		order by $x
		return <li>{data($x)}</li>
	}
</ul>