import { TaskService } from './../services/task/task.service';
import { Task } from './../shared/models/task.model';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Status } from '../shared/models/status.enum';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  taskList: Task[];

  constructor(private taskService: TaskService, public datepipe: DatePipe) { }

  ngOnInit(): void {
    this.listAll();
  }


  listAll() {
    this.taskService.listAll().then(response => {
      this.taskList = response
    }).catch(err => {
      console.log(err);
    });
  }

  changeStatus(task:Task) {
    if(task.status == Status.ANDAMENTO) {
      task.status = Status.CONCLUÍDA;
    }

    this.update(task);
  }

  remove(task:Task) {
    task.status = Status.REMOVIDA
    this.update(task);
  }

  update(task:Task) {
    this.taskService.update(task).then(response => {
      this.listAll();
    }).catch(err => {
      console.log(err);
    });
  }


}
