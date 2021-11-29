<?php
require_once "db.php";
require_once 'security.php';

if(isset($_POST["id"]) && !empty($_POST["id"])){
	$sql = "DELETE FROM buchung WHERE id = ?";
    
    if($stmt = mysqli_prepare($link, $sql)){
        mysqli_stmt_bind_param($stmt, "i", $param_id);
		$param_id = trim($_POST["id"]);
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
<html lang="en">
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
                        <h2>Buchung löschen</h2>
                    </div>
                    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
                        <div class="alert alert-danger fade in">
                            <input type="hidden" name="id" value="<?php echo trim($_GET["id"]); ?>"/>
                            <p>Buchung wirklich löschen?</p><br>
                            <p>
                                <input type="submit" value="Löschen" class="btn btn-danger">
                                <a href="index.php" class="btn btn-default">Abrechen</a>
                            </p>
                        </div>
                    </form>
                </div>
            </div>        
        </div>
    </div>
</body>
</html>