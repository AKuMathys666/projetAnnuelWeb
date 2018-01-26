<?php

function getListProjects($header, $data_string)
{
    $ch = curl_init('http://localhost:8080/projects');                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);                

    $response = curl_exec($ch);
    
    $result = json_decode($response, true);
    
    return $result;
}

function getListTasks($header, $data_string)
{
    $ch = curl_init('http://localhost:8080/tasks');                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);                

    $response = curl_exec($ch);
    
    $result = json_decode($response, true);
    
    return $result;
}

function getListUsers($header, $data_string)
{
    $ch = curl_init('http://localhost:8080/users');                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);                

    $response = curl_exec($ch);
    
    $result = json_decode($response, true);
    
    return $result;
}

function getTeam($header, $data_string, $id)
{
    $ch = curl_init('http://localhost:8080/teams/' . $id);                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);                

    $response = curl_exec($ch);
    
    $result = json_decode($response, true);
    
    return $result;
}

function displayMembers($members, $users, $team)
{
    for($i=0; $i<count($members); $i++){
        echo "<tr>";
        echo "<td>".findUserById($users, $members[$i])['email']."</td>";
        echo "<td>".findUserById($users, $members[$i])['last_name']."</td>";
        echo "<td>".findUserById($users, $members[$i])['first_name']."</td>";
        echo "<td><a href='delete-member.php?id=".$team."&idUser=".$members[$i]."'>
						<button type='submit' class='btn btn-danger' name='idUser'>Delete</button>
					</a></td>";
        echo "</tr>";
    }
    
}


function createProject($header, $data_string)
{
    $ch = curl_init('http://localhost:8080/projects');                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);
    
    $response = curl_exec($ch);
    
    $result = json_decode($response, true);
    
    return $result;
}

function displayProjects($listProjects, $listUser, $listTask, $header, $data_string){
	$nb=count($listProjects);
	for($i=0;$i<$nb;$i++)
	{
		echo "<tr>";
        //echo "<td>".$listProjects[$i]['_id']."</td>";    
        echo "<td>".$listProjects[$i]['title']."</td>";
        echo "<td>".findUserById($listUser, $listProjects[$i]['creator'])['email']."</td>";
        //echo "<td>".$listProjects[$i]['creator']."</td>";
        //echo "<td>".getTeam($header, $data_string, $listProjects[$i]['equipe'])['members']."</td>";
        
        $members = getTeam($header, $data_string, $listProjects[$i]['equipe'])['members'];
        echo "<td>";
        echo "<a href='members.php?team=".getTeam($header, $data_string, $listProjects[$i]['equipe'])['_id']."'>";
        
        
        for($n=0; $n<count($members); $n++){
            echo findUserById($listUser, $members[$n])['email'];
            echo "</br>";
        }
        
        
        echo "</a>";
        echo "</td>";
        //echo "<td>".$listProjects[$i]['__v']."</td>";
        //echo "<td>".$listProjects[$i]['tasks']."</td>";
        echo "<td>";
        for($j=0; $j<count($listProjects[$i]['tasks']); $j++){
            //print($listProjects[$i]['tasks'][$j]);
            if(findTaskById($listTask, $listProjects[$i]['tasks'][$j])!=null){
                echo findTaskById($listTask, $listProjects[$i]['tasks'][$j])['title'];
                echo "</br>";
            }
            else{
                echo null;
            }
        }
        //print_r($listProjects[$i]['tasks']);
        echo "</td>";
		echo "</tr>";
	}
}

function displayTasks($listTasks, $listUser, $listProject){
	$nb=count($listTasks);
	for($i=0;$i<$nb;$i++)
	{
		echo "<tr>";
        //echo "<td>".$listTasks[$i]['_id']."</td>";    
        echo "<td>".$listTasks[$i]['title']."</td>";
        echo "<td>".$listTasks[$i]['startDate']."</td>";
        if($listTasks[$i]['endDate']!= null){
            echo "<td>".$listTasks[$i]['endDate']."</td>";
        }else{
            echo "<td> NULL </td>";
        }
        //echo "<td>".$listTasks[$i]['owner']."</td>";
        echo "<td>".findUserById($listUser, $listTasks[$i]['owner'])['email']."</td>";
        echo "<td>".findProjectById($listProject, $listTasks[$i]['project'])['title']."</td>";
        //echo "<td>".$listTasks[$i]['project']."</td>";
        //echo "<td>".$listTasks[$i]['__v']."</td>";
        //echo "<td>".$listTasks[$i]['tasks']."</td>";
        /*if(count($listTasks[$i])>6){
            echo "<td>".displayTime(abs(strtotime($listTasks[$i]['startDate'])-strtotime($listTasks[$i]['endDate'])))."</td>";
        }else{
            echo "<td> 0 seconds </td>";
        }*/
        if(count($listTasks[$i])>7 && $listTasks[$i]['endDate'] != null){    
            echo "<td>".displayTime($listTasks[$i]['times'])."</td>";
        }else{
            echo "<td> 0 seconds </td>";
        }
		echo "</tr>";
	}
}

function displayUsers($listUsers, $listTask){
	$nb=count($listUsers);
	for($i=0;$i<$nb;$i++)
	{
		echo "<tr>";
        //echo "<td>".$listUsers[$i]['_id']."</td>";    
        echo "<td>".$listUsers[$i]['email']."</td>";
        echo "<td>".$listUsers[$i]['role']."</td>";
        if(count($listUsers[$i])>6){
            echo "<td>";
            for($j=0; $j<count($listUsers[$i]['tasks']); $j++){
                if(findTaskById($listTask, $listUsers[$i]['tasks'][$j])!=null){
                    echo findTaskById($listTask, $listUsers[$i]['tasks'][$j])['title'];
                    echo "</br>";
                }
                //print($listUsers[$i]['tasks'][$j]);
                //echo "</br>";
            }
            echo "</td>";
        }else{
            echo "<td> NULL </td>";
        }
        echo "<td>".$listUsers[$i]['last_name']."</td>";
        echo "<td>".$listUsers[$i]['first_name']."</td>";
        //echo "<td>".$listUsers[$i]['__v']."</td>";
        //echo "<td>".$listUsers[$i]['tasks']."</td>";
		echo "</tr>";
	}
}

function signUp($header, $data_string){
        
    $ch = curl_init('http://localhost:8080/users');                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(                                                                          
        'Content-Type: application/json',                                                                                
        'Content-Length: ' . strlen($data_string))                                                                       
    );                                                                                                                   

    $response = curl_exec($ch);
    
    $result = json_decode($response, true);
    
    return $result;
}

function addMember($header, $data_string, $user, $team){
        
    $ch = curl_init('http://localhost:8080/teams/'.$team.'/'.$user);                                                                      
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(                                                                          
        'Content-Type: application/json',                                                                                
        'Content-Length: ' . strlen($data_string))                                                                       
    );                                                                                                                   

    $response = curl_exec($ch);
    
    $result = json_decode($response, true);
    
    return $result;
}

function printAUser($user){
    
    echo "<table class=".'"table-css table-bordered table-responsive table-hover table-striped"'.">";
    echo "<tr>
    <th class='title-css'>Id</th>
    <th class='title-css'>Email</th>
    <th class='title-css'>Role</th>
    <th class='title-css'>Tasks</th>
    <th class='title-css'>Last name</th>
    <th class='title-css'>First name</th>
    <th class='title-css'>__v</th>
    </tr>";
    
    echo "<tr>";
    echo "<td>".$user['_id']."</td>";    
    echo "<td>".$user['email']."</td>";
    echo "<td>";
        echo "ID: ".$user['role']['_id']."</br>";
        echo "Title: ".$user['role']['title']."</br>";
        echo "Level: ".$user['role']['level']."</br>";
        echo "__v: ".$user['role']['__v']."</br>";
    echo "</td>";
    if(count($user)>6){
        echo "<td>";
        for($j=0; $j<count($user['tasks']); $j++){
            print($user['tasks'][$j]);
            echo "</br>";
        }
        echo "</td>";
    }else{
        echo "<td> NULL </td>";
    }
    echo "<td>".$user['last_name']."</td>";
    echo "<td>".$user['first_name']."</td>";
    echo "<td>".$user['__v']."</td>";
    //echo "<td>".$user['tasks']."</td>";
    echo "</tr>";
    
}

function findProjectByTitle($projectList, $title){
    print(count($projectList));
    print($title);
    for($i=0; $i<count($projectList); $i++){
        print($projectList[$i]['_id']);
        if($projectList[$i]['title'] == $title){
            print(count($projectList));
            print($projectList[$i]['_id']);
            return $projectList[$i]['_id'];
        }
    }
    return null;
}

function findTaskById($taskList, $id){
    for($i=0; $i<count($taskList); $i++){
        if($taskList[$i]['_id'] == $id){
            return $taskList[$i];
        }
    }
    return null;
}

function findUserById($userList, $id){
    for($i=0; $i<count($userList); $i++){
        if($userList[$i]['_id'] == $id){
            return $userList[$i];
        }
    }
    return null;
}

function findProjectById($projectList, $id){
    for($i=0; $i<count($projectList); $i++){
        if($projectList[$i]['_id'] == $id){
            return $projectList[$i];
        }
    }
    return null;
}


function displayTime($time){
    $rest= $time;
    $days = round($rest/86400);
    $rest = $rest%86400;
    $hours = round($rest/3600);
    $rest = $rest%3600;
    $minutes = round($rest/60);
    $rest = $rest%60;
    $secondes = $rest;
    return $days."days ".$hours."hours ".$minutes."minutes ".$secondes."secondes";
}


?>