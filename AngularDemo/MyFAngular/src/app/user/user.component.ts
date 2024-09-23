import { Component } from '@angular/core';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  username = 'tveleza2';
  isLoggedIn = false;

  greet(username: string){
    alert('Hola de nuevo '+username+'!!!',)
  }
}
