<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <h2 class="mt-4 mb-4 text-center">Sammensæt din helt egen carport herunder</h2>
    <br>
        <form novalidate>
            <div class="form-group">
                <label for="laengde">Carportens længde</label>
                <select class="form-control" id="laengde" required>
                    <option>200cm</option>
                    <option>300cm</option>
                    <option>400cm</option>
                </select>
            </div>

            <div class="form-group">
                <label for="bredde">Carportens bredde</label>
                <select class="form-control" id="bredde" required>
                    <option>150cm</option>
                    <option>200cm</option>
                    <option>250cm</option>
                    <option>300cm</option>
                </select>
            </div>

            <div class="form-group">
                <label for="tag">Carportens tagtype</label>
                <select class="form-control" id="tag" required>
                    <option>Fladt</option>
                    <option>Med rejsning</option>
                </select>
            </div>

            <div class="custom-control custom-switch">
                <input type="checkbox" class="custom-control-input" id="shed" onchange="document.getElementById('shedtype').disabled = !this.checked;">
                <label class="custom-control-label" for="shed">Jeg ønsker et skur</label>
            </div>
            <div class="form-group">
                <label for="shedtype">Skur type</label>
                <select class="form-control" id="shedtype" disabled>
                    <option>Hel bredde</option>
                    <option>Halv bredde</option>
                </select>
            </div>

            <div class="form-group">
                <label for="name">Din email</label>
                <input type="name" class="form-control" id="name" aria-describedby="emailHelp" required>
            </div>
            <div class="form-group">
                <label for="email">Din email</label>
                <input type="email" class="form-control" id="email" aria-describedby="emailHelp" required>
            </div>
            <div class="form-group">
                <label for="phone">Dit telefonnummer</label>
                <input type="phone" class="form-control" id="phone" required>
            </div>


            <button type="submit" class="btn btn-primary">Send forespørgelse</button>
        </form>