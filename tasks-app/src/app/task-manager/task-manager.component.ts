import { TaskService } from './../services/task/task.service';
import { Task } from './../shared/models/task.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-task-manager',
  templateUrl: './task-manager.component.html',
  styleUrls: ['./task-manager.component.css']
})
export class TaskManagerComponent implements OnInit {

  formTask: FormGroup;
  taskData: Task = new Task();
  titleText = "Registro";
  btnText = "Cadastrar";

  constructor(private formBuilder: FormBuilder, private activatedroute: ActivatedRoute, private router: Router, private taskService: TaskService) { }

  ngOnInit(): void {
    this.createForm(new Task());
    this.activatedroute.paramMap.subscribe(paramMap => {
      if (paramMap.get("id") != null) {
        this.infoTask(Number.parseInt(paramMap.get("id")));
        this.titleText = "Atualização"
        this.btnText = "Atualizar"
      } else {
        this.createForm(new Task());
      }
    });
  }

  createForm(task: Task) {
    this.formTask = this.formBuilder.group({
      title: [task.title],
      description: [task.description],
    })
  }

  infoTask(id: number) {
    this.taskService.findById(id).then(response => {
      this.taskData = response;
      this.completForm(this.taskData);
    }).catch(err => {
      console.log(err);
    });
  }

  completForm(task: Task) {
    this.formTask.controls.title.setValue(task.title);
    this.formTask.controls.description.setValue(task.description);
  }


  onSubmit() {
    if (this.formTask.valid) {
      this.taskData.title = this.formTask.value.title;
      this.taskData.description = this.formTask.value.description;
      if (this.taskData.id == undefined) {
        this.taskService.register(this.taskData).then(response => {
          this.formTask.reset();
          this.toPageList();
        }).catch(err => {
          console.log(err);
        });
      } else {
        this.taskData.changeFields = true;
        this.taskService.update(this.taskData).then(response => {
          this.formTask.reset();
          this.toPageList();
        }).catch(err => {
          console.log(err);
        })
      }
    }
  }

  toPageList() {
    this.router.navigateByUrl('listall');
  }

}
