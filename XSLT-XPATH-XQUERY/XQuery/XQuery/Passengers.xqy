<ul>
	{
		for $x in doc("Passengers.xml")/passengers/passenger
			where $x/class="Business"
				order by $x/firstname
					return <li>{data($x/firstname)} {data($x/lastname)}</li>
	}
</ul>