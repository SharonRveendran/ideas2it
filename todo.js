(function() { 
    let id = 6;
    let list = [
        {
            id : "category1",
            icon : "fa fa-sun",
            name : "My Day",
            //taskList : []
        },
        {
            id : "category2",
            icon : "fa fa-star",
            name : "Important",
            //taskList : []
        },
        {
            id : "category3",
            icon : "fa fa-calendar",
            name : 'Planned',
            //taskList : []
        },
        {
            id : "category4",
            icon : "fa fa-user",
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
    let categoryId;
    let categoryListBlock = document.getElementById('category-list');
    let categoryInput = document.getElementById('add-category');
    let categoryContainer = document.getElementById('category');
    let taskTitleblock = document.getElementsByClassName('title')[0];
    let taskInput = document.getElementsByClassName('task-input')[0];
    function init() { 
        document.getElementsByClassName("center-container")[0].style.visibility = 'hidden';
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
    }
    function categoryList() {
        for (let i = 0; i < list.length; i++) {
            renderCategoryList(list[i]);
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
                id: "category" + id++,
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
        const taskUl = document.getElementsByClassName('task-ul')[0];
        categoryId = event.target.id;
        document.getElementsByClassName("center-container")[0].style.visibility = 'visible';
        taskTitleblock.innerHTML =  document.getElementById(categoryId).innerHTML;
        while (taskUl.firstChild) {
            taskUl.removeChild(taskUl.firstChild);
        }
        for (let i = 0; i < taskList.length; i++) {
            if (categoryId == taskList[i].id) {
                renderTask(taskList[i]);
            }
        }
    }
    function addTask() {
        let newTask = {
            id : categoryId,
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
        taskLi.appendChild(taskSpan);
        icon.className = "far fa-star";
        taskLi.appendChild(icon);
        taskUl.appendChild(taskLi);
        document.getElementsByClassName("tasks")[0].appendChild(taskUl);
    }
    init();
})();
    
