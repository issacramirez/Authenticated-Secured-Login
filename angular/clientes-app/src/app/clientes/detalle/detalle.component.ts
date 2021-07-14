import { Component, Input, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { ClienteService } from '../cliente.service';
import { ModalService } from './modal.service';
import Swal from 'sweetalert2';
import { HttpEventType } from '@angular/common/http';

@Component({
  selector: 'detalle-cliente',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {
  @Input() cliente!: Cliente;
  titulo: string = "Detalle de cliente";
  public imagenSeleccionada: File;
  progreso: number = 0;

  constructor(private clienteService: ClienteService,
              public modalService: ModalService,) { }

  ngOnInit(): void {}

  seleccionarFoto(event) {
    this.imagenSeleccionada = event.target.files[0];
    this.progreso = 0;
    console.log(this.imagenSeleccionada);
    if(this.imagenSeleccionada.type.indexOf('image') < 0){
      Swal.fire('Error', 'Debe ser formato de imagen', 'error');
      this.imagenSeleccionada = null;
    }
  }

  subirFoto() {
    if (!this.imagenSeleccionada) {
      Swal.fire('Error', 'Debe Seleccionar una foto', 'error');
    } else {
      this.clienteService.subirFoto(this.imagenSeleccionada, this.cliente.id).subscribe(event => {
        if(event.type === HttpEventType.UploadProgress){
          this.progreso = Math.round((event.loaded/event.total)*100);
        }else if(event.type === HttpEventType.Response){
          let response: any = event.body;
          this.cliente = response.cliente as Cliente;
          this.modalService.notificarUpload.emit(this.cliente);
          Swal.fire('La foto se ha subido correctamente', response.mensaje , 'success');
        }
        
      });
    }
  }

  cerrarModal() {
    this.modalService.cerrarModal();
    this.imagenSeleccionada = null;
    this.progreso = 0;
  }

}
