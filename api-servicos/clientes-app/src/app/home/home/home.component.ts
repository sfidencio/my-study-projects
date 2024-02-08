import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private route: Router, private activeRoute: ActivatedRoute,private auth:AuthService) { }

  ngOnInit(): void {
  }

  efetuarLogoff(event: Event) {
    //event.preventDefault;
    localStorage.removeItem('access_token');
    this.route.navigate(['/login']);
  }

  obterUsuarioLogado() {
    return this.auth.obterUsuarioLogado();
  }

}
