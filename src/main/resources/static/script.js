(function () {

    console.log("Checking");
    document.getElementById("greeting").innerHTML = "Hello, user!";

    const viewGoalsButton = document.getElementById("viewGoalsButton");
    viewGoalsButton.addEventListener("click", () => { getAllGoals(); });
    const CreateGoalButton = document.getElementById("createGoalButton");
    CreateGoalButton.addEventListener("click", () => { createCreateForm(); });
    const goalsBox = document.getElementById("goalsBox");














    //READ GOAL FUNCTIONS


    const createGoalBoxes = (goals) => {
        goalsBox.innerHTML = "";
        for (let i in goals) {
            console.log(goals[i]);
            let goalBox = document.createElement("div");
            goalBox.classList.add("goalBox", "centeredColumn");
            goalBox.addEventListener("click", function click() {
                goalBox.removeEventListener("click", click);
                openGoal(goalBox, goals[i])
            });

            let goalTitleBox = document.createElement("div");
            goalTitleBox.classList.add("goalTitleBox");
            goalTitleBox.classList.add("centeredContent");

            let deleteButton = document.createElement("button");
            deleteButton.innerHTML = "Delete";
            deleteButton.onclick = function () { deleteGoal(goals[i]).then(function () { getAllGoals() }) };

            let editGoalButton = document.createElement("button");
            editGoalButton.innerHTML = "Edit";

            let goalDescriptionBox = document.createElement("div");
            goalDescriptionBox.classList.add("goalDescriptionBox");
            goalDescriptionBox.classList.add("centeredContent");


            let goalTitle = document.createElement("h3");
            goalTitle.innerHTML = goals[i].goalName;

            let goalDesc = document.createElement("p");
            goalDesc.innerHTML = goals[i].goalDescription;


            editGoalButton.onclick = function () { editGoal(goals[i], goalTitleBox, goalDescriptionBox, goalTitle, goalDesc, editGoalButton)};

            goalTitleBox.appendChild(deleteButton);
            goalTitleBox.appendChild(editGoalButton);
            goalTitleBox.appendChild(goalTitle);
            goalDescriptionBox.appendChild(goalDesc);

            goalBox.appendChild(goalTitleBox);
            goalBox.appendChild(goalDescriptionBox);

            goalsBox.appendChild(goalBox);
        }
    }

    getAllGoals = () => {

        fetch("/getAllGoals", { method: "GET" })
            .then(res => res.json().then(body => createGoalBoxes(body)))
            .catch(err => console.error(err + "WAGUAN"));
    }
















    //CREATE GOAL FUNCTIONS

    function handleForm(event) { event.preventDefault(); }

    createCreateForm = () => {
        let newGoalBox = document.createElement("div");
        newGoalBox.classList.add("newGoalBox");

        let header = document.createElement("div");
        header.classList.add("newGoalHeader");
        header.classList.add("centeredContent");
        newGoalBox.appendChild(header);

        let head = document.createElement("h2");
        head.innerHTML = "Create a new Life Goal!";
        header.appendChild(head);

        let form = document.createElement("form");
        form.addEventListener('submit', handleForm);

        let titleLabel = document.createElement("label");
        titleLabel.innerHTML = "Goal title:";

        let title = document.createElement("input");

        let descriptionLabel = document.createElement("label");
        descriptionLabel.innerHTML = "Add a description:";

        let description = document.createElement("textarea");
        let submitButton = document.createElement("button");
        submitButton.setAttribute("type", "submit");
        submitButton.setAttribute("value", "Submit");
        submitButton.innerHTML = "Create";
        submitButton.onclick = function () { createGoal(title.value, description.value) };

        form.appendChild(titleLabel)
        form.appendChild(title);
        form.appendChild(descriptionLabel);
        form.appendChild(description);
        form.appendChild(submitButton);


        newGoalBox.appendChild(form);

        document.body.appendChild(newGoalBox);

    }

    createGoal = (name, description) => {
        console.log('create Goal called');
        fetch("/createGoal", {
            method: "POST",
            body: JSON.stringify(
                {
                    "goalName": `${name}`,
                    "goalDescription": `${description}`
                }
            ),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(res => res.json().then(body => console.log(body)))
            .catch(err => console.error(err + "WAGUAN"));
    }




    //UPDATE GOAL FUNCTIONS

    editGoal = (goal, goalTitleBox, goalDescriptionBox, goalTitle, goalDescription, editGoalButton) => {

        let titleEditor = document.createElement("input");
        let descriptionEditor = document.createElement("input");


        titleEditor.value = goalTitle.innerHTML;
        goalTitleBox.removeChild(goalTitle);
        goalTitleBox.appendChild(titleEditor);

        descriptionEditor.value = goalDescription.innerHTML;
        goalDescriptionBox.removeChild(goalDescription);
        goalDescriptionBox.appendChild(descriptionEditor);

        editGoalButton.innerHTML = "UPDATE";
        editGoalButton.onclick = function () { updateGoal(goal, titleEditor.value, descriptionEditor.value).then(function () { getAllGoals() }) };
    }





    async function updateGoal(goal, name, description) {
        let response = await fetch(`/updateGoal/${goal.goalId}`, {
            method: "PATCH",
            body: JSON.stringify(
                {
                    "goalName": `${name}`,
                    "goalDescription": `${description}`
                    //  "goal": goal
                }
            ),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(res => res.json().then(body => console.log(body)))
            .catch(err => console.error(err + "WAGUAN"));

        return response;
    }





    //DELETE GOAL FUNCTIONS

    async function deleteGoal(goal) {
        let response = await fetch(`/deleteGoal/${goal.goalId}`, {
            method: "DELETE"
        })
            .then(res => console.log(res))
            .catch(err => console.error(err + "WAGUAN"));

        return response;
    }
















































    //READ TASKS FUNCTIONS

    createTaskBoxes = (tasksBox, tasks) => {
        tasksBox.innerHTML = "";
        for (let i in tasks) {
            console.log(tasks[i].taskName, tasks[i].taskDescription);
            let taskBox = document.createElement("div");
            taskBox.classList.add("taskBox", "centeredColumn");

            let deleteButton = document.createElement("button");
            deleteButton.innerHTML = "Delete";
            deleteButton.onclick = function () { deleteTask(tasks[i]) };

            let editTaskButton = document.createElement("button");
            editTaskButton.innerHTML = "Edit";

            let taskTitleBox = document.createElement("div");
            taskTitleBox.classList.add("taskTitleBox");
            taskTitleBox.classList.add("centeredContent");
            taskTitleBox.appendChild(deleteButton);
            taskTitleBox.appendChild(editTaskButton);


            let taskDescriptionBox = document.createElement("div");
            taskDescriptionBox.classList.add("taskDescriptionBox");
            taskDescriptionBox.classList.add("centeredContent");


            let taskTitle = document.createElement("h5");
            taskTitle.innerHTML = tasks[i].taskName;

            let taskDesc = document.createElement("p");
            taskDesc.innerHTML = tasks[i].taskDescription;



            editTaskButton.onclick = function () { editTask(tasks[i], taskTitleBox, taskDescriptionBox, taskTitle, taskDesc, editTaskButton) };


            taskTitleBox.appendChild(taskTitle);
            taskDescriptionBox.appendChild(taskDesc);

            taskBox.appendChild(taskTitleBox);
            taskBox.appendChild(taskDescriptionBox);

            tasksBox.appendChild(taskBox);
        }
    }

    async function fetchTasks(goal, tasksBox) {
        const response = await fetch(`/getAllTasks/${goal.goalId}`, {
            method: "GET"
        })
            .then(res => res.json().then(body => {
                console.log(body);
                createTaskBoxes(tasksBox, body);
            }))
            .catch(err => console.error(err + "WAGUAN"));
        return response;
    }


    openGoal = (goalBox, goal) => {
        console.log("openGoal called");
        goalBox.style.height = "60vh";
        let listTitleBox = document.createElement("div");
        listTitleBox.classList.add("listTitleBox");
        let listTitle = document.createElement("h4");
        listTitle.innerHTML = "Tasks:";

        listTitleBox.appendChild(listTitle);


        let tasksBox = document.createElement("div");
        tasksBox.classList.add("tasksBox", "centeredColumn");

        let addTaskButtonBox = document.createElement("div");
        addTaskButtonBox.classList.add("listTitleBox", "centeredColumn");

        let addTaskButton = document.createElement("button");
        addTaskButton.innerHTML = "Create Task";
        addTaskButtonBox.onclick = function () { createCreateTaskForm(tasksBox, goal) };

        addTaskButtonBox.appendChild(addTaskButton);

        goalBox.appendChild(listTitleBox);
        goalBox.appendChild(tasksBox);
        goalBox.appendChild(addTaskButtonBox);



        fetchTasks(goal, tasksBox);
        console.log("Open Goal complete");
    }


































    //CREATE TASK FUNCTIONS


    createCreateTaskForm = (tasksBox, goal) => {
        let newTaskBox = document.createElement("div");
        newTaskBox.classList.add("newGoalBox");

        let header = document.createElement("div");
        header.classList.add("newGoalHeader");
        header.classList.add("centeredContent");
        newTaskBox.appendChild(header);

        let head = document.createElement("h2");
        head.innerHTML = "Create a new Task!";
        header.appendChild(head);

        let form = document.createElement("form");
        form.addEventListener('submit', handleForm);

        let titleLabel = document.createElement("label");
        titleLabel.innerHTML = "Task title:";

        let title = document.createElement("input");

        let descriptionLabel = document.createElement("label");
        descriptionLabel.innerHTML = "Add a description:";

        let description = document.createElement("textarea");
        let submitButton = document.createElement("button");
        submitButton.setAttribute("type", "submit");
        submitButton.setAttribute("value", "Submit");
        submitButton.innerHTML = "Create";
        submitButton.onclick = function () { createTask(title.value, description.value, goal) };

        form.appendChild(titleLabel)
        form.appendChild(title);
        form.appendChild(descriptionLabel);
        form.appendChild(description);
        form.appendChild(submitButton);


        newTaskBox.appendChild(form);

        document.body.appendChild(newTaskBox);
    }


    createTask = (name, description, goal) => {


        console.log(JSON.stringify(goal));
        fetch("/createTask", {
            method: "POST",
            body: JSON.stringify(
                {
                    "taskName": `${name}`,
                    "taskDescription": `${description}`,
                    "goal": goal
                }
            ),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(res => res.json().then(body => console.log(body)))
            .catch(err => console.error(err + "WAGUAN"));

        getAllGoals();

    }














    //UPDATE TASK FUNCTIONS

    editTask = (task, taskTitleBox, taskDescriptionBox, taskTitle, taskDescription, editTaskButton) => {

        let titleEditor = document.createElement("input");
        let descriptionEditor = document.createElement("input");


        titleEditor.value = taskTitle.innerHTML;
        taskTitleBox.removeChild(taskTitle);
        taskTitleBox.appendChild(titleEditor);

        descriptionEditor.value = taskDescription.innerHTML;
        taskDescriptionBox.removeChild(taskDescription);
        taskDescriptionBox.appendChild(descriptionEditor);

        editTaskButton.innerHTML = "UPDATE";
        editTaskButton.onclick = function () { updateTask(task, titleEditor.value, descriptionEditor.value) };
    }





    updateTask = (task, name, description) => {
        fetch(`/updateTask/${task.taskId}`, {
            method: "PATCH",
            body: JSON.stringify(
                {
                    "taskName": `${name}`,
                    "taskDescription": `${description}`,
                }
            ),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(res => res.json().then(body => console.log(body)))
            .catch(err => console.error(err + "WAGUAN"));

        getAllGoals();
    }






    //DELETE TASKS FUNCTIONS

    deleteTask = (task) => {
        console.log("delete task called", task.taskId);
        fetch(`/deleteTask/${task.taskId}`, {
            method: "DELETE"
        })
            .then(res => console.log(res))
            .catch(err => console.error(err + "WAGUAN"));

        getAllGoals();
    }



})();