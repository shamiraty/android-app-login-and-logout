<!doctype html>
<html>
<head>
<style>
body
{
background:linear-gradient(to left,#5bc0de ,white )
}
</style>
<link rel="stylesheet" a href="css/bootstrap.css">
<link rel="stylesheet" a href="custom.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
<link rel="stylesheet"type="text/css"href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css"/>
<link rel="stylesheet" type="text/css"href="https://cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css"/>	
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/2.0.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/2.0.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/2.0.1/js/buttons.print.min.js"></script>
<link rel="stylesheet" a href="css/bootstrap.css">
<meta charset="utf-8">
<title>Home</title>
</head>
<body>
<div class="card text-center">
  <div class="card-header">
    <ul class="nav nav-tabs card-header-tabs">
      <li class="nav-item">
        <a class="nav-link active" href="addproduct.php">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="saleproduct.php">Items</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">All Sales</a>
      </li>
    </ul>
  </div>
  <div class="card-body">
  <h5 class="card-title">Sales Transaction</h5>
  </div>
</div>	
	<script>
$(document).ready(function() {
    $('#myTable').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    } );
} );
</script>
	<div class="card">
		<div class="card-header">,,,</div>
		<div class="card-body">
	
			
		<!--weka table headings hapa------>
			
<table id="myTable"class="diplay">
			<thead class="bg bg-info">
				<tr>
				<th class="bg text-white">username</th>
					<th class="bg text-white">department</th>
					<th class="bg text-white">email</th>
					<th class="bg text-white">role</th>
					<th class="bg text-white">date</th>
					<th class="bg text-white">Action</th>
				
				</tr>
	
	</thead>
	<tbody>
			
			
	
		<!--table headings mwisho------>
		
		<!--PHP hapa------>
	
		
		<?php
		
$con=mysqli_connect('localhost','root','','demoApp');
if($con)
{
	$sql="SELECT * FROM users";
	$results=mysqli_query($con,$sql);
	if(mysqli_num_rows($results)>0)
	{
		while($row=mysqli_fetch_array($results))
		{
			echo '<tr><td class="bg bg-warning"><button class="btn btn-primary btn-xs w-100">'.$row['username'].'</button></td>';
			echo '<td >'.$row['department'].'</td>';
			echo '<td>'.$row['email'].'</td>';
			echo '<td>'.$row['role'].'</td>';
			echo '<td>'.$row['date'].'</td>';
			echo '<td><span class="badge bg bg-primary">edit</span><span class="badge bg bg-danger">delete</span></td>';
		}
		echo '</tr></tbody></table>';
		mysqli_free_result($results);
	}
}
		else{
			echo 'no connection';
		}
		
		
		?>
	
	<!--PHP mwisho------>
</div>
</div>	
</body>
</html>