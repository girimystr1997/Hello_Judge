

function create() {
    
    //declaration and initialisation of values
    var name = document.getElementById('name').value;
    var college_name = document.getElementById('college_name').value;
    var dept_and_branch = document.getElementById('dept_and_branch').value;
    var year = document.getElementById('year').value;
    var judge_one = document.getElementById('judge_one').value;
    var judge_two = document.getElementById('judge_two').value;
    var time = document.getElementById('time').value;
    
    // Get a reference to the database service
   var database = firebase.database();
    
    firebase.database().ref('participents/' + name).set({
    name: name,
    college_name: college_name,
    dept_and_branch : dept_and_branch,
    year : year,
    judge_one : judge_one,
    judge_two : judge_two,
    time : time
  });

    alert("sucess");
    //to test value
    //alert(name+"\n"+college_name+"\n"+dept_and_branch+"\n"+year);
    
}
