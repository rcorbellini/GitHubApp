package com.corbellini.core.errors

class RemoteApiExceptions(val text: String) : Exception("Erro ao chamar api interna. ($text)")

class UnknownExceptions : Exception("Erro Interno.")