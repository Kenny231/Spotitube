<html>
	<body>
		<h2>Playlist toevoegen</h2>
		<form action="addPlaylistView" method="POST">
			<p>Naam: <input type="text" name="name">
			<button type="submit">Submit</button></p>
			<input type="hidden" name="owner" value="${owner}">
		</form>
		<br>
		<input type="button" onclick="location.href='viewPlaylists?owner=${owner}';" value="Terug" />
	</body>
</html>