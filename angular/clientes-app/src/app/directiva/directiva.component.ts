import { Component } from '@angular/core';

@Component({
  selector: 'app-directiva',
  templateUrl: './directiva.component.html',
})
export class DirectivaComponent {

  listaCursos: string[] = ['Typescript', 'Javascript', 'Java SE', 'C#']

  habilitar: boolean = true;

  constructor() { }

  setHabilitar() {
    this.habilitar = (this.habilitar==true)? false: true;
  }

}
