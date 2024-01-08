import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { InfrastructureModule } from '../adapter/infrastructure/infrastructure.module';
import { PresentationModule } from '../adapter/presentation/presentation.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [PresentationModule, RouterOutlet, InfrastructureModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'sharecipe';
}
