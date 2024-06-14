<?php

$con=mysqli_connect('localhost','root','','demoApp');
if($con)
{
	$username="sami";
	if(isset($_POST['username']))
	{
		$username=$_POST['username'];
	}
	$password="1234";
	if(isset($_POST['password']))
	{
		$password=$_POST['password'];
	}
	
	$sql="SELECT * FROM users where username='$username' AND password='$password'";
	$result=mysqli_query($con,$sql);
	if(mysqli_num_rows($result)>0)
	{
		
		$row=mysqli_fetch_assoc($result);
		$status="ok";
		$resultCode=1;
		$department=$row['department'];
		$userExist=$row['username'];
		echo json_encode(array('status'=>$status,'resultCode'=>$resultCode,'department'=>$department,'userExist'=>$userExist));
		
		
	}
	else{
		$status="ok";
		$resultCode=0;
		echo json_encode(array('status'=>$status,'resultCode'=>$resultCode));
	}
}
else
{
	$status="failed";
	echo json_encode(array('status'=>$status),JSON_FORCE_OBJECT);
}

mysqli_close($con);



?>