function agreeForm(f) {
		// Если поставлен флажок, снимаем блокирование кнопки
		if (f.agree.checked) {

			f.email.disabled = 0;
			f.name.disabled = 0;
			f.change.disabled = 0;

		}
		// В противном случае вновь блокируем кнопку
		else {
			f.email.disabled = 1;
			f.name.disabled = 1;
			f.change.disabled = 1;
		}

	}

	function updateForm(f) {
		// Если поставлен флажок, снимаем блокирование кнопки
		if (f.agree.checked) {
			f.namber.disabled = 0;
			f.carrying.disabled = 0;
			f.amount.disabled = 0;
			f.engine.disabled = 0;
			f.comments.disabled = 0;
			f.oldtype.hidden = 1;
			f.type.hidden = 0;
			f.oldstatus.hidden = 1;
			f.status.hidden = 0;
			f.change.disabled = 0;
		}
		// В противном случае вновь блокируем кнопку
		else {
			f.namber.disabled = 1;
			f.carrying.disabled = 1;
			f.amount.disabled = 1;
			f.engine.disabled = 1;
			f.comments.disabled = 1;
			f.oldtype.hidden = 0;
			f.type.hidden = 1;
			f.oldstatus.hidden = 0;
			f.status.hidden = 1;
			f.change.disabled = 1;
		}
	}
	function formDriver(f) {
	    // Если поставлен флажок, снимаем блокирование кнопки
	   if (f.agree.checked) {

		   f.comments.hidden = 0;
		   f.status.hidden = 0;
		   f.change.disabled = 0;

	   }
	    // В противном случае вновь блокируем кнопку
	    else {
	    	f.comments.hidden = 1;
	 	   	f.status.hidden = 1;
	 	   	f.change.disabled = 1;
	    }

	}