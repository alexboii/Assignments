<?php
require_once 'controller/Controller.php';
session_start ();
$_SESSION ["errorEventName"] = "";
$_SESSION ["errorDateFormat"] = "";
$_SESSION ["errorStartTimeFormat"] = "";
$_SESSION ["errorEndTimeFormat"] = "";

$c = new Controller ();
try {

	$c->createEvent( $_POST ['event_name'], $_POST['event_date'], $_POST['start_time'], $_POST['end_time']);
	
} catch ( Exception $e ) {
	$errors = explode ( "@", $e->getMessage () );
	foreach ( $errors as $error ) {
		if (substr ( $error, 0, 1 ) == "1") {
			$_SESSION ["errorEventName"] = substr ( $error, 1 );
		}
		if (substr ( $error, 0, 1 ) == "2") {
			$_SESSION ["errorDateFormat"] = substr ( $error, 1 );
		}
		if (substr ( $error, 0, 1 ) == "3") {
			$_SESSION ["errorStartTimeFormat"] = substr ( $error, 1 );
		}
		if (substr ( $error, 0, 1 ) == "4") {
			$_SESSION ["errorEndTimeFormat"] = substr ( $error, 1 );
		}
	}
}
?>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="0; url=/EventRegistration-PHP/" />
</head>
</html>