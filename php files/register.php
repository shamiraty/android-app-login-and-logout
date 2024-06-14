<?php

$con=mysqli_connect('localhost','root','','demoApp');
if($con)
{
	$username="aminah";
	if(isset($_POST['username']))
	{
		$username=$_POST['username'];
	}
	$password="1234";
	if(isset($_POST['password']))
	{
		$password=$_POST['password'];
	}
	
		$email="aminah@gmail.com";
	if(isset($_POST['email']))
	{
		$email=$_POST['email'];
	}
		$department="Human Resource";
	if(isset($_POST['department']))
	{
		$department=$_POST['department'];
	}
	$sql="SELECT * FROM users where username='$username' or email='$email'";
	$result=mysqli_query($con,$sql);
	if(mysqli_num_rows($result)>0)
	{
		
		$row=mysqli_fetch_assoc($result);
		$status="ok";
		$resultCode=0;
		$department=$row['department'];
		$userExist=$row['username'];
		$email=$row['email'];
		$role=$row['role'];
		echo json_encode(array('status'=>$status,'resultCode'=>$resultCode,'department'=>$department,'userExist'=>$userExist,'email'=>$email,'role'=>$role));
		
		
	}
	else{
		$sql="INSERT INTO users (username,password,email,department) VALUES('$username','$password','$email','$department')";
		$result=mysqli_query($con,$sql);
		if($result)
		{
			$status="ok";
			$resultCode=1;
			echo json_encode(array('status'=>$status,'resultCode'=>$resultCode));
		}
		else
		{
			$status="failed";
			echo json_encode(array('status'=>$status),JSON_FORCE_OBJECT);
		}
	}
}
else
{
	$status="dberror";
	echo json_encode(array('status'=>$status),JSON_FORCE_OBJECT);
}

mysqli_close($con);



?>