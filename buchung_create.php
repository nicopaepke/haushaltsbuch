<?php
require_once "db.php";
require_once 'security.php';

if($_SERVER["REQUEST_METHOD"] == "POST"){
	$sql = "INSERT INTO buchung (quellkonto, zielkonto, datum, betrag, beschreibung) VALUES (?, ?, ?, ?, ?)";
	if($stmt = mysqli_prepare($link, $sql)){
		mysqli_stmt_bind_param($stmt, "sssss", $param_quellkonto, $param_zielkonto, $param_datum, $param_betrag, $param_beschreibung);
		$param_quellkonto = $_POST['quellkonto'];
		$param_zielkonto = $_POST['zielkonto'];
		$param_datum = $_POST['datum'];
		$param_betrag = $_POST['betrag'];
		$param_beschreibung = $_POST['beschreibung'];
		if(mysqli_stmt_execute($stmt)){        
			header("location: index.php");
			exit();
		} else{
			echo "Something went wrong. Please try again later.";
		}
	}
	mysqli_stmt_close($stmt);
	mysqli_close($link);
}	
?>

<html> 
<head>
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head> 
<body>
<div class="wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
					<h2>Neue Buchung</h2>
				</div>
				<form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
					<div class="form-group">
						<label for="datum">Datum</label>
						<input type="date" name="datum" class="form-control" value="<?=date('Y-m-d')?>" required="true">
                    </div>
					<div class="form-group">
						<label for="quellkonto">Quellkonto</label>
						<select class="form-control" name="quellkonto" required="true">
						<option disabled selected value> -- bitte auswählen -- </option>
							<?php
								$sql = "SELECT id, name FROM konto ORDER BY name";
								$konten = mysqli_query($link, $sql);
								while($konto = mysqli_fetch_array($konten)) {
									echo '<option value='.$konto['id'].'>'.$konto['name'].'</option>';
								}
								mysqli_free_result($konten);
							?>							
						</select>
					</div>
					<div class="form-group">
						<label for="zielkonto">Zielkonto</label>
						<select class="form-control" name="zielkonto"  required="true">
						<option disabled selected value> -- bitte auswählen -- </option>
							<?php
								$sql = "SELECT id, name FROM konto ORDER BY name";
								$konten = mysqli_query($link, $sql);
								while($konto = mysqli_fetch_array($konten)) {
									echo '<option value='.$konto['id'].'>'.$konto['name'].'</option>';
								}
								mysqli_free_result($konten);
							?>							
						</select>
					</div>
					<div class="form-group">
						<label for="beschreibung">Beschreibung / Zweck</label>
						<textarea name="beschreibung" class="form-control" required="true"></textarea>
                    </div>
					<div class="form-group">
						<label for="betrag">Betrag</label>
						<input type="number" name="betrag" class="form-control" 
							value="" step=".01" required="true">
                    </div>
					<input class="btn btn-primary" type="submit" value="Speichern">
					<a href="index.php" class="btn btn-default">Abrechen</a>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>

<?php
	mysqli_close($link);
?>