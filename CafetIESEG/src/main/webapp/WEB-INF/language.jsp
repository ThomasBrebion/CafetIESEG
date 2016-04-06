<%@ page pageEncoding="utf-8" %>		
		<form>
            <select id="language" name="language" onchange="submit()">
                <option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            </select>
        </form>