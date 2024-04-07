package br.com.cursoudemy.productapi.modules.jwt.dto;

import java.util.UUID;


import java.util.Objects;

public class JwtResponse {

    private UUID id;

    private String name;

    private String email;

    public JwtResponse() {
    }

    public JwtResponse(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JwtResponse)) {
            return false;
        }
        JwtResponse jwtResponse = (JwtResponse) o;
        return Objects.equals(id, jwtResponse.id) && Objects.equals(name, jwtResponse.name)
                && Objects.equals(email, jwtResponse.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                "}";
    }

}
