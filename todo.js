(function() { 
    let categoryId = 6;
    let list = [
        {
            id : "category1",
            icon : "fa fa-sun",
            name : "My Day",
        },
        {
            id : "category2",
            icon : "fa fa-star",
            name : "Important",
            
        },
        {
            id : "category3",
            icon : "fa fa-calendar",
            name : 'Planned'
        },
        {
            id : "category4",
            icon : "fa fa-user",
            name : "Assign to you"
        },
        {
            id : "category5",
            icon : "fa fa-home",
            name : "Task"
        }
    ];
    //let myDayTaskList[];
    let categoryList = document.getElementById('category-list');
    let categoryInput = document.getElementById('add-category');
    let categoryContainer = document.getElementById('category');
    let taskTitleblock = document.getElementsByClassName('title')[0];
    function init() { 
        document.getElementsByClassName("center-container")[0].style.visibility = 'hidden';
        renderCategoryList();
        categoryInput.addEventListener('keypress', function(event){ 
            addCategory(event);
        });
        categoryContainer.addEventListener("click", function(event){
            if ("SPAN" === event.target.tagName) {
               renderTask(event);
            }
        });
    }
    function renderTask(event) {
        document.getElementsByClassName("center-container")[0].style.visibility = 'visible';
        taskTitleblock.innerHTML =  document.getElementById(event.target.id).innerHTML;
    }
    function renderCategoryList() {
        for (let i = 0; i < list.length; i++) {
            const category = document.createElement('li');
            const icon = document.createElement('i');
            icon.setAttribute("class", list[i].icon);
            const span = document.createElement("span");
            span.setAttribute("id", list[i].id);
            span.appendChild(document.createTextNode(list[i].name));
            category.appendChild(icon);
            category.appendChild(span);
            categoryList.appendChild(category);
        }
    }
    function addCategory(event) {
            if (event.key == 'Enter') {
                if (categoryInput.value == '') {
                    categoryInput.value = 'Untitled list';
                }
                var category = document.createElement('li');
                const icon = document.createElement('i');
                icon.setAttribute("class","fa fa-list");
                const span = document.createElement("span");
                span.setAttribute("id", "category" + categoryId++);
                span.appendChild(document.createTextNode(categoryInput.value));
                category.appendChild(icon);
                category.appendChild(span);
                categoryList.appendChild(category);
                categoryInput.value = '';
            }  
    }
    init();
})();
    
