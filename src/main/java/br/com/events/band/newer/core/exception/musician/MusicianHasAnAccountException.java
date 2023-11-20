package br.com.events.band.newer.core.exception.musician;

import br.com.events.band.older.infrastructure.exception.badRequest.NotFoundException;

public class MusicianHasAnAccountException extends NotFoundException {
    public MusicianHasAnAccountException() {
        super(
                "Músico possui conta!",
                "O músico informado já possui uma conta e apenas ele poderá realizar esta ação!"
        );
    }
}
