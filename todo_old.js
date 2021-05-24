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
    let categoryId = 6; 
    let taskId = 0; 
    subTaskId = 0; 
    let taskList = []; 
    let subTaskList = []; 
    let currentCategoryId; 
    let subTaskTitle = document.getElementsByClassName('sub-task-title')[0];
    let centerContainer = document.getElementsByClassName('center-container')[0];
    let categoryListBlock = $('#category-list');//document.getElementById('category-list');
    let categoryInput = $('#add-category');//document.getElementById('add-category');
    let categoryContainer = $('#category');//document.getElementById('category');
    let taskTitleblock =  document.getElementsByClassName('title')[0];
    let taskInput = document.getElementsByClassName('task-input')[0];
    let taskUl = $('.task-ul');//document.getElementsByClassName("task-ul")[0];
    let rightContainer = $('.right-container');//document.getElementsByClassName("right-container")[0];
    let stepInput = document.getElementsByClassName('step-input')[0];
    let stepList = document.getElementsByClassName('step-list')[0];
    
    function init() { 
        document.getElementsByClassName("center-container")[0].style.visibility = 'hidden';
        rightContainer.hide();//style.display = 'none';
        renderinitialCategoryList();
        categoryInput.keypress(addCategory);
        categoryContainer.click(renderTaskContainer);
        taskInput.addEventListener("keypress", addTask);
        taskUl.click(renderSubTaskContainer);//.addEventListener("click", renderSubTaskContainer);
        stepInput.addEventListener("keypress", addSubTask);
        taskUl.click(addToImportant);//addEventListener("click", addToImportant);
        taskUl.click(strike);//addEventListener("click", strike);
    }

    /** 
     * This function will render initial catogery list
     */
     function renderinitialCategoryList() {
        for(let initialCategory of list) {
            renderCategoryList(initialCategory);
        }
    }

    /** 
     * This function will add and render new category
     * @param event category input event
     */
     function addCategory(event) {
        if (event.key == 'Enter') {
            if (categoryInput.val() == '') {
                categoryInput.val('Untitled list');// = 'Untitled list';
            }
            let obj = {
                id: "category" + categoryId++,
                icon: 'fa fa-list',
                name: categoryInput.val(),
            }
            list.push(obj);
            renderCategoryList(obj);
            categoryInput.val('');//clear();//.val() = '';
        }  
    }

    /** 
     * This function will render given category
     * @param value category object
     */
     function renderCategoryList(value) {
        const category = $('<li/>');//document.createElement('li');
        const icon = $('<i/>');//document.createElement('i');
        icon.addClass(value.icon);//setAttribute("class", value.icon);
        const span = $('<span/>');//document.createElement("span");
        span.attr('id', value.id);//setAttribute("id", value.id);
        span.append(document.createTextNode(value.name));
        category.append(icon);
        category.append(span);
        categoryListBlock.append(category);
    }

    /**
     * This function will render center container
     * @param event click event for category 
     */
     function renderTaskContainer(event) {
        if ("SPAN" === event.target.tagName) {
            rightContainer.hide();//style.display = 'none';
            centerContainer.style.width = "80%";
            const taskUl = document.getElementsByClassName('task-ul')[0];
            currentCategoryId = event.target.id;
            document.getElementsByClassName("center-container")[0].style.visibility = 'visible';
            taskTitleblock.innerHTML = document.getElementById(currentCategoryId).innerHTML;//val($("#"+currentCategoryId).val());//document.getElementById(currentCategoryId).innerHTML);
            while (taskUl.firstChild) {
                taskUl.removeChild(taskUl.firstChild);
            }
            for (let taskElement of taskList) {
                if ((taskElement.id).includes(currentCategoryId) || (taskElement.id).includes('importantTask')) {
                    if (event.target.innerHTML == 'Important') {
                        if((taskElement.id).includes('importantTask') || (taskElement.id).includes(currentCategoryId)) {
                            renderTask(taskElement);
                        }
                    } else if (!(taskElement.id).includes('importantTask')) {
                        renderTask(taskElement);
                    }
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
                id : currentCategoryId + "task" + taskId++ ,
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
        const taskUl = document.getElementsByClassName('task-ul')[0];
        const taskLi = document.createElement("li");
        const taskCheckBox = document.createElement("input");
        const taskSpan = document.createElement("span");
        const icon = document.createElement("i");
        taskLi.id = newTask.id + "x";
        icon.id = newTask.id + "y";
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

    /**
     * This function will render right container 
     * @param event click event for task list
     */
     function renderSubTaskContainer(event) {
        if (event.target.tagName === 'SPAN') {
            rightContainer.show();
            //rightContainer.style.display = 'inline-block';
            centerContainer.style.width = "58%";
            //centerContainer.className = "center-container-half";
            //centerContainer.classList.add("center-container-half");
            //centerContainer.className += "center-container-half";
            subTaskTitle.innerHTML = event.target.innerHTML;
            while (stepList.firstChild) {
                stepList.removeChild(stepList.firstChild);
            }
            for (let subtaskElement of subTaskList) {
                if ((subtaskElement.id).includes(subTaskTitle.innerHTML)) {
                    renderSubTask(subtaskElement);
                }
            }
        }
    }

    /**
     * This function will add and render new steps for a task
     * @param event keypress event for step input
     */
    function addSubTask(event) {
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

    /**
     * function to add task to important 
     * @param event click event for task list
     */
     function addToImportant(event) {
        if ("I" === event.target.tagName) {
            for( let currentTask of taskList) {
                if(currentTask.id === (event.target.id).substring(0,event.target.id.length-1)) {
                    let obj = {
                        id: "importantTask" + taskId++,
                        icon: 'fa fa-list',
                        name: document.getElementById((event.target.id).substring(0,event.target.id.length-1)).innerHTML,
                    }
                    taskList.push(obj); 
                }
            }
            event.target.className = 'fas fa-star';
            event.target.style.color = 'grey';
        }     
    }

    /**
     * function to strike out the clicked task 
     * @param event click event for task list
     */
    function strike(event) {
        if ("INPUT" === event.target.tagName) {
            if(event.target.checked == true) {
                document.getElementsByClassName("steps-checkbox")[0].checked = true;
            } else {
                document.getElementsByClassName("steps-checkbox")[0].checked = false;
            }
        }
    }
    init();
})();
    
