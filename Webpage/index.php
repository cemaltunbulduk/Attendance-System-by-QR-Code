<?php
require_once 'phpqrcode/qrlib.php';

if(!isset($_COOKIE['lecturer'])) header("location:login.php"); 

$path = 'images/';
$file = $path.uniqid().".png";

$timestamp = date('d.m.Y');
$text = $timestamp;

QRcode::png($text, $file, 'L', 10);

echo "<center><img src='".$file."'><center>";
echo "<a href=logout.php>Logout</a></center>";
header("Refresh:15; url=studentlist.php");
?>