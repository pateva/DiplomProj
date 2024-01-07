import { Component } from '@angular/core';
import { MaterialModule } from '../material/material.module';


@Component({
  selector: 'app-single-application',
  standalone: true,
  imports: [MaterialModule],
  templateUrl: './single-application.component.html',
  styleUrl: './single-application.component.scss'
})


export class SingleApplicationComponent {
  longText = `The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog
  from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was
  originally bred for hunting.`;
}
