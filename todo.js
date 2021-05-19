(function() { 
    let cid = 6;
    let tid = 0;
    subTaskId = 0;
    let list = [
        {
            id : "category1",
            icon : "fa fa-sun",
            name : "My Day",
            //taskList : []
        },
        {
            id : "category2",
            icon : "far fa-star",
            name : "Important",
            //taskList : []
        },
        {
            id : "category3",
            icon : "far fa-calendar-alt",
            name : 'Planned',
            //taskList : []
        },
        {
            id : "category4",
            icon : "far fa-user",
            name : "Assign to you",
            //taskList : []
        },
        {
            id : "category5",
            icon : "fa fa-home",
            name : "Task",
            //taskList : []
        }
    ];
    let taskList = [];
    let subTaskList = [];
    let categoryId;
    let subTaskTitle = document.getElementsByClassName('sub-task-title')[0];
    let centerContainer = document.getElementsByClassName('center-container')[0];
    let categoryListBlock = document.getElementById('category-list');
    let categoryInput = document.getElementById('add-category');
    let categoryContainer = document.getElementById('category');
    let taskTitleblock = document.getElementsByClassName('title')[0];
    let taskInput = document.getElementsByClassName('task-input')[0];
    let taskUl = document.getElementsByClassName("task-ul")[0];
    let rightContainer = document.getElementsByClassName("right-container")[0];
    let stepInput = document.getElementsByClassName('step-input')[0];
    let stepList = document.getElementsByClassName('step-list')[0]; 
    function init() { 
        document.getElementsByClassName("center-container")[0].style.visibility = 'hidden';
        rightContainer.style.display = 'none';
        categoryList();
        categoryInput.addEventListener('keypress', function(event){ 
            addCategory(event);
        });
        categoryContainer.addEventListener("click", function(event){
            if ("SPAN" === event.target.tagName) {
               renderTaskContainer(event);
            }
        });
        taskInput.addEventListener("keypress", function(event) {
            if (event.key === 'Enter' && taskInput.value != '') {
                addTask();
            } 
        });
        taskUl.addEventListener("click", function(event) {
            if (event.target.tagName === 'SPAN') {
                renderSubTaskContainer(event);
            } 
        });
        stepInput.addEventListener("keypress", function(event) {
            if (event.key === 'Enter' && stepInput.value != '') {
                addStep();
            } 
        });
    }
    function addStep() {console.log(subTaskTitle.innerHTML);
        let newStep = {
            id : subTaskTitle.innerHTML + subTaskId++,
            name : stepInput.value
        }
        stepInput.value = '';
        subTaskList.push(newStep);
        renderSubTask(newStep);
    }
    function renderSubTask(newStep) {
        const subTaskList = document.createElement("li");
        const subTaskCheckBox = document.createElement("input");
        const subTaskSpan = document.createElement("span");
        const icon = document.createElement("i");
        subTaskCheckBox.setAttribute("type", "checkbox");
        subTaskList.appendChild(subTaskCheckBox);
        subTaskSpan.appendChild(document.createTextNode(newStep.name));
        subTaskList.appendChild(subTaskSpan);
        icon.className = "fa fa-times";
        subTaskList.appendChild(icon);
        subTaskList.appendChild(document.createElement("hr"));
        stepList.appendChild(subTaskList);
    }
    function categoryList() {
        for (let i = 0; i < list.length; i++) {
            renderCategoryList(list[i]);
        }
    }
    function renderSubTaskContainer(event) {
        rightContainer.style.display = 'inline-block';
        centerContainer.style.width = "58%";
        subTaskTitle.innerHTML = event.target.innerHTML;
        while (stepList.firstChild) {
            stepList.removeChild(stepList.firstChild);
        }
        for (let i = 0; i < subTaskList.length; i++) {
            if ((subTaskList[i].id).includes(subTaskTitle.innerHTML)) {
                renderSubTask(subTaskList[i]);
            }
        }
    }
    function renderCategoryList(value) {
        const category = document.createElement('li');
        const icon = document.createElement('i');
        icon.setAttribute("class", value.icon);
        const span = document.createElement("span");
        span.setAttribute("id", value.id);
        span.appendChild(document.createTextNode(value.name));
        category.appendChild(icon);
        category.appendChild(span);
        categoryListBlock.appendChild(category);
    }
    function addCategory(event) {
        if (event.key == 'Enter') {
            if (categoryInput.value == '') {
                categoryInput.value = 'Untitled list';
            }
            let obj = {
                id: "category" + cid++,
                icon: 'fa fa-list',
                name: categoryInput.value,
                //taskList : []
            }
            list.push(obj);
            renderCategoryList(obj);
            categoryInput.value = '';
        }  
    }
    function renderTaskContainer(event) {
        rightContainer.style.display = 'none';
        centerContainer.style.width = "80%";
        const taskUl = document.getElementsByClassName('task-ul')[0];
        categoryId = event.target.id;
        document.getElementsByClassName("center-container")[0].style.visibility = 'visible';
        taskTitleblock.innerHTML =  document.getElementById(categoryId).innerHTML;
        while (taskUl.firstChild) {
            taskUl.removeChild(taskUl.firstChild);
        }
        for (let i = 0; i < taskList.length; i++) {
            if ((taskList[i].id).includes(categoryId)) {
                renderTask(taskList[i]);
            }
        }
    }
    function addTask() {
        let newTask = {
            id : categoryId + "task" + tid++ ,
            name : taskInput.value
        }
        taskInput.value = '';
        taskList.push(newTask);
        renderTask(newTask);
    }
    function renderTask(newTask) {
        const taskUl = document.getElementsByClassName('task-ul')[0];
        const taskLi = document.createElement("li");
        const taskCheckBox = document.createElement("input");
        const taskSpan = document.createElement("span");
        const icon = document.createElement("i");
        taskCheckBox.setAttribute("type", "checkbox");
        taskLi.appendChild(taskCheckBox);
        taskSpan.appendChild(document.createTextNode(newTask.name));
        taskSpan.id = newTask.id;
        taskLi.appendChild(taskSpan);
        icon.className = "far fa-star";
        taskLi.appendChild(icon);
        taskLi.appendChild(document.createElement("hr"));
        taskUl.appendChild(taskLi);
        document.getElementsByClassName("tasks")[0].appendChild(taskUl);
    }
    init();
})();
    
