import { Component, ViewChild, ViewContainerRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { ThesisApplicationPartial } from '../interfaces/thesis-application-partial.model';
import { SingleApplicationComponent } from '../single-application/single-application.component';


@Component({
  selector: 'app-applications',
  standalone: true,
  imports: [MaterialModule, SingleApplicationComponent],
  templateUrl: './applications.component.html',
  styleUrl: './applications.component.scss'
})
export class ApplicationsComponent {
  isToBeDisplayed = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router) { }



  THESIS_APPLICATIONS: ThesisApplicationPartial[] = [
    { id: 1, title: 'Thesis on Angular', department: 'IT', createdAt: '2024-01-10', status: 'Submitted' },
    { id: 2, title: 'Exploring TypeScript', department: 'Cinema', createdAt: '2024-01-15', status: 'Pending' },
    { id: 3, title: 'Exploring TypeScript', department: 'Cinema', createdAt: '2024-01-15', status: 'Pending' },
    { id: 4, title: 'Exploring TypeScript', department: 'Cinema', createdAt: '2024-01-15', status: 'Pending' },
    { id: 5, title: 'Exploring TypeScript', department: 'Cinema', createdAt: '2024-01-15', status: 'Pending' },
    { id: 6, title: 'Exploring TypeScript', department: 'Cinema', createdAt: '2024-01-15', status: 'Pending' },
  ]

  displayedColumns: string[] = ['id', 'title', 'department', 'createdAt', 'status', 'actions'];
  dataSource = this.THESIS_APPLICATIONS;

  onClick() {
    this.isToBeDisplayed = !this.isToBeDisplayed;
  }

}
