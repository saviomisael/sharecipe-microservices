import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PresentationModule } from '../adapter/presentation/presentation.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [PresentationModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'sharecipe';
}
