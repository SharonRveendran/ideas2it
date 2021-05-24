(function() { 
    let list = [
        {
            id : "category1",
            icon : "fa fa-sun",
            name : "My Day",
        },
        {
            id : "category2",
            icon : "far fa-star",
            name : "Important",
        },
        {
            id : "category3",
            icon : "far fa-calendar-alt",
            name : 'Planned',
        },
        {
            id : "category4",
            icon : "far fa-user",
            name : "Assign to you",
        },
        {
            id : "category5",
            icon : "fa fa-home",
            name : "Task",
        }
    ];
    let cid = 6; //id for new catogery
    let tid = 0;  //id for tsask
    subTaskId = 0; //id for subtask
    let taskList = []; //list for all tasks
    let subTaskList = []; //list for all subtasks
    let categoryId; //id for current category
    let subTaskTitle = $('.sub-task-title');//document.getElementsByClassName('sub-task-title')[0];
    let centerContainer = $('.center-container');//document.getElementsByClassName('center-container')[0];
    let categoryListBlock = $('#category-list');//document.getElementById('category-list');//$('#category-list');
    let categoryInput = $('#add-category');//#document.getElementById('add-category');
    let categoryContainer = $('#category');//document.getElementById('category');
    let taskTitleblock = $('.title');//document.getElementsByClassName('title')[0];
    let taskInput = $('.task-input');//document.getElementsByClassName('task-input')[0];
    let taskUl = $('.task-ul');//document.getElementsByClassName("task-ul")[0];
    let rightContainer = $('.right-container');//document.getElementsByClassName("right-container")[0];
    let stepInput = $('.step-input');//document.getElementsByClassName('step-input')[0];
    let stepList = $('.step-list');//document.getElementsByClassName('step-list')[0];
    
    function init() { 
        $('.center-container').hide;//style.visibility = 'hidden';//getElementsByClassName("center-container")[0].style.visibility = 'hidden';
        rightContainer.css('display', 'none');//style.display = 'none';
        categoryList();
        categoryInput.keypress(addCategory);//addEventListener('keypress', addCategory);
        categoryContainer.click(renderTaskContainer);//addEventListener("click", renderTaskContainer);
        taskInput.keypress(addTask);//taskInput.addEventListener("keypress", addTask);
        taskUl.keypress(renderSubTaskContainer);//addEventListener("click", renderSubTaskContainer);
        stepInput.keypress(addStep);//addEventListener("keypress", addStep);
    }

    /**
     * This function will add and render new steps for a task
     * @param event keypress event for step input
     */
    function addStep(event) {
        if (event.key === 'Enter' && stepInput.value != '') {
            let newStep = {
                id : subTaskTitle.innerHTML + subTaskId++,
                name : stepInput.value
            }
            stepInput.value = '';
            subTaskList.push(newStep);
            renderSubTask(newStep);
        } 
    }

    /**
     * This function will render given subtasks
     * @param newStep new step object
     */
    function renderSubTask(newStep) {
        const subTaskList = $('<li/>');//document.createElement("li");
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

    /** 
     * This function will render initial catogery list
     */
    function categoryList() {
        for (let i = 0; i < list.length; i++) {
            renderCategoryList(list[i]);
        }
    }
    
    /**
     * This function will render right container
     * @param event click event for task list
     */
    function renderSubTaskContainer(event) {
        if (event.target.tagName === 'SPAN') {
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
    }

    /** 
     * This function will render given category
     * @param value category object
     */
    function renderCategoryList(value) {
        const category = document.createElement('li');
        const icon = document.createElement('i');
        icon.setAttribute("class", value.icon);
        const span = document.createElement("span");
        span.setAttribute("id", value.id);
        span.appendChild(document.createTextNode(value.name));
        category.appendChild(icon);
        category.appendChild(span);
        categoryListBlock.appendTo(category);//.appendChild(category);
    }

    /** 
     * This function will add and render new category
     * @param event category input event
     */
    function addCategory(event) {
        if (event.key == 'Enter') {
            if (categoryInput.value == '') {
                categoryInput.value = 'Untitled list';
            }
            let obj = {
                id: "category" + cid++,
                icon: 'fa fa-list',
                name: categoryInput.value,
            }
            list.push(obj);
            renderCategoryList(obj);
            categoryInput.value = '';
        }  
    }

    /**
     * This function will render center container
     * @param event click event for category 
     */
    function renderTaskContainer(event) {
        if ("SPAN" === event.target.tagName) {
            rightContainer.css('display', 'none');//style.display = 'none';
            centerContainer.css('width','80%');//style.width = "80%";
            const taskUl = $('.task-ul');//document.getElementsByClassName('task-ul')[0];
            categoryId = event.target.id;
            $('.center-container').style.visibility = 'visible';//getElementsByClassName("center-container")[0].style.visibility = 'visible';
            taskTitleblock.innerHTML = document.getElementById(categoryId).innerHTML;
            while (taskUl.firstChild) {
                taskUl.removeChild(taskUl.firstChild);
            }
            for (let i = 0; i < taskList.length; i++) {
                if ((taskList[i].id).includes(categoryId)) {
                    renderTask(taskList[i]);
                }
            }
        }
    }

    /** 
     * This function will add and render new task
     *  @param event keypress event for task input 
     */
    function addTask(event) {
        if (event.key === 'Enter' && taskInput.value != '') {
            let newTask = {
                id : categoryId + "task" + tid++ ,
                name : taskInput.value
            }
            taskInput.value = '';
            taskList.push(newTask);
            renderTask(newTask);
        } 
    }

    /** 
     * This function will render given task
     * @param newTask new task object
     */
    function renderTask(newTask) {
        const taskUl = $('.task-ul');//document.getElementsByClassName('task-ul')[0];
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
        taskUl.appendTo(taskLi);//appendChild(taskLi);
        $('.tasks').appendTo(taskUl);//getElementsByClassName("tasks")[0].appendChild(taskUl);
    }
    init();
})();
    
