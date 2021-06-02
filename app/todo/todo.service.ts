import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  constructor() { }
  taskContainerVisibility = false;
  currentCategoryName: any;
  currentCategoryId !: string;
  currentTaskList: any;
  currentTask: any;
  currentTaskId: any;
  subTaskContainerVisibility = false;
  currentSubTaskList: {
    id: string;
    name: string;
  }[] = [];
  categoryList: Array<{
    id: string; icon: string; name: string;
    taskList: Array<{ id: string; name: string; subTaskList: Array<{ id: string; name: string; }>; }>;
  }> = [
      {
        id: "category1",
        icon: "fas fa-sun",
        name: "My Day",
        taskList: []
      },
      {
        id: "category2",
        icon: "far fa-star",
        name: "Important",
        taskList: []
      },
      {
        id: "category3",
        icon: "fa fa-calendar",
        name: 'Planned',
        taskList: []
      },
      {
        id: "category4",
        icon: "fa fa-user",
        name: "Assign to you",
        taskList: []
      },
      {
        id: "category5",
        icon: "fa fa-home",
        name: "Task",
        taskList: []
      }
    ]

  /**
   * Methode to return category list
   * @returns category list
   */
  getCategoryList() {
    return this.categoryList;
  }

  /**
   * Methode to set current category id
   * @param categoryId category id
   */
  setCategoryId(categoryId: string) {
    this.currentCategoryId = categoryId;
  }

  /**
   * Methode to get current category name
   * @param categoryId  current category id
   * @returns current category name
   */
  getCategoryName(categoryId: string) {
    for (let category of this.categoryList) {
      if (category.id == categoryId) {
        this.currentCategoryName = category.name;
      }
    }
    return this.currentCategoryName;
  }
  
  /**
   * Method to add category
   * @param newCategory new category object
   */
  addCategory(newCategory: { id: string; icon: string; name: string; taskList: never[]; }) {
    this.categoryList.push(newCategory);
  }

  /**
   * Method to add task
   * @param newTask new task object 
   */
  addTask(newTask: any) {
    for (const obj of this.categoryList) {
      if (obj.id == this.currentCategoryId) {
        obj.taskList.push(newTask);
      }
    }
  }

  /**
   * Method to add sub task
   * @param newSubTask new task object
   * @param task_id  task id
   * @param category_id category id
   */
  addSubTask(newSubTask: { id: any; name: any; }, task_id: string, category_id: string) {
    for (const obj of this.categoryList) {
      if (obj.id == category_id) {
        for (let task of obj.taskList) {
          if (task.id == task_id) {
            task.subTaskList.push(newSubTask);
          }
        }
      }
    }
  }

  /**
   * Method to get current task ist
   * @returns current task list
   */
  getTaskList() {
    for (const obj of this.categoryList) {
      if (obj.id == this.currentCategoryId) {
        this.currentTaskList = obj.taskList;
      }
    }
    console.log("inside getTaskLIST");
    console.log(this.currentTaskList)
    return this.currentTaskList;
  }

  /**
   * 
   * @param task_id task id
   * @param category_id category id
   * @returns current subtask list
   */
  getSubTaskList(task_id: string, category_id: string) {
    for (let i = 0; i < this.categoryList.length; i++) {
      if (category_id == this.categoryList[i].id) {
        for (let j = 0; j < this.categoryList[i].taskList.length; j++) {
          if (task_id == this.categoryList[i].taskList[j].id) {
            this.currentSubTaskList = this.categoryList[i].taskList[j].subTaskList;
          }
        }
      }
    }
    return this.currentSubTaskList;
  }
}
