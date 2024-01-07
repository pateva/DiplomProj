import { Component } from '@angular/core';
import { ActivatedRoute,Router } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { ThesisApplicationPartial } from '../interfaces/thesis-application-partial.model';


@Component({
  selector: 'app-applications',
  standalone: true,
  imports: [MaterialModule],
  templateUrl: './applications.component.html',
  styleUrl: './applications.component.scss'
})
export class ApplicationsComponent {
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

}
