/*
 *   Copyright (c) 2024 Stefano Marano https://github.com/StefanoMarano80017
 *   All rights reserved.

 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at

 *   http://www.apache.org/licenses/LICENSE-2.0

 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.g2.Components;

import com.g2.Interfaces.ServiceManager;

/**
 * Componente che utilizza il ServiceManager per eseguire logica di controllo o
 * di business, senza restituire oggetti da inserire nel modello.
 */
public class ServiceLogicComponent extends GenericLogicComponent {

    private final String serviceName;
    private final String action;
    protected ServiceManager serviceManager;
    private final Object[] params;
    protected String ErrorCode = "Service_Logic_error_1";

    /**
     * Costruttore per il componente.
     *
     * @param serviceManager il ServiceManager per gestire la richiesta.
     * @param serviceName il nome del servizio su cui eseguire la logica.
     * @param action l'azione da eseguire sul servizio.
     * @param params eventuali parametri per l'azione del servizio.
     */
    public ServiceLogicComponent(ServiceManager serviceManager, String serviceName, String action, Object... params) {
        this.serviceManager = serviceManager;
        this.serviceName = serviceName;
        this.action = action;
        this.params = params;
    }

    /**
     * Esegue la logica utilizzando il ServiceManager. il risultato è
     * considerato booleano. per altre implementazioni va sovraccaricato questo
     * metodo.
     *
     * @return true se la logica è stata eseguita con successo, altrimenti
     * false.
     */
    @Override
    public boolean executeLogic() {
        try {
            // Esegue la logica richiesta tramite il ServiceManager
            Boolean result = (Boolean) serviceManager.handleRequest(serviceName, action, params);
            return result;
        } catch (Exception e) {
            // Gestione dell'eccezione, ad esempio log dell'errore
            System.err.println("Errore durante l'esecuzione della logica: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

}
