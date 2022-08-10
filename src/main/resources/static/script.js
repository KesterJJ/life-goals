(function () {

    console.log("Checking");
    document.getElementById("greeting").innerHTML = "Hello, user!";

    const viewGoalsButton = document.getElementById("viewGoalsButton");
    viewGoalsButton.addEventListener("click", () => { getAllGoals(); });
    const CreateGoalButton = document.getElementById("createGoalButton");
    CreateGoalButton.addEventListener("click", () => { createCreateForm(); });
    const goalsBox = document.getElementById("goalsBox");














    //READ FUNCTIONS


    const createGoalBoxes = (goals) => {
        goalsBox.innerHTML = "";
        for (let i in goals) {
            console.log(goals[i]);
            let goalBox = document.createElement("div");
            goalBox.classList.add("goalBox");
            goalBox.addEventListener("click", function() {openGoal(goalBox, goals[i]);});

            let goalTitleBox = document.createElement("div");
            goalTitleBox.classList.add("goalTitleBox");
            goalTitleBox.classList.add("centeredContent");

            let goalDescriptionBox = document.createElement("div");
            goalDescriptionBox.classList.add("goalDescriptionBox");
            goalDescriptionBox.classList.add("centeredContent");


            let goalTitle = document.createElement("h3");
            goalTitle.innerHTML = goals[i].goalName;

            let goalDesc = document.createElement("p");
            goalDesc.innerHTML = goals[i].goalDescription;

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
        submitButton.onclick = function() {createGoal(title.value, description.value)};

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
            }})
            .then(res => res.json().then(body => console.log(body)))
            .catch(err => console.error(err + "WAGUAN"));
    }















    //READ TASKS FUNCTIONS

    createTaskBoxes = (goalBox, tasks) => {
        for (let i in tasks) {
            console.log(tasks[i].taskName, tasks[i].taskDescription);
            let taskBox = document.createElement("div");
            taskBox.classList.add("taskBox");

            let taskTitleBox = document.createElement("div");
            taskTitleBox.classList.add("taskTitleBox");
            taskTitleBox.classList.add("centeredContent");

            let taskDescriptionBox = document.createElement("div");
            taskDescriptionBox.classList.add("taskDescriptionBox");
            taskDescriptionBox.classList.add("centeredContent");


            let taskTitle = document.createElement("h4");
            taskTitle.innerHTML = tasks[i].taskName;

            let taskDesc = document.createElement("p");
            taskDesc.innerHTML = tasks[i].taskDescription;

            taskTitleBox.appendChild(taskTitle);
            taskDescriptionBox.appendChild(taskDesc);

            taskBox.appendChild(taskTitleBox);
            taskBox.appendChild(taskDescriptionBox);

            goalBox.appendChild(taskBox);
        }
    }

    getTasks = (goalBox, goal) => {
        goalBox = goalBox;
        fetch(`/getAllTasks/${goal.goalId}`, {
            method: "GET"})
            .then(res => res.json().then((goalBox, body) => createTaskBoxes(goalBox, body)))
            .catch(err => console.error(err + "WAGUAN"));

    }

    openGoal = (goalBox, goal) => {
        goalBox.style.height = "60vh";
        console.log("openGoal");
        getTasks(goalBox, goal);
    }




















})();