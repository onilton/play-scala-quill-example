package models

import javax.inject.{ Inject, Singleton }

import scala.concurrent.{ Future, ExecutionContext }
import io.getquill.H2JdbcContext
import io.getquill.SnakeCase
import db.DbContext


/**
 * A repository for people.
 *
 * @param dbConfigProvider The Play db config provider. Play will inject this for you.
 */
@Singleton
class PersonRepository @Inject() (dbContext: DbContext)(implicit ec: ExecutionContext) {
  // This imports is important, it brings db into scope, which will let you do the actual db operations.
  import dbContext.ctx._

  /**
   * The starting point for all queries on the people table.
   */
  private val people = quote(querySchema[Person]("people"))

  /**
   * Create a person with the given name and age.
   *
   * This is a synchronous operation, it will return the created person, which can be used to obtain the
   * id for that person.
   */
  def create(name: String, age: Int): Person = {
    val person = Person(0, name, age)

    person.copy(id = run(people.insert(lift(person)).returningGenerated(_.id)))
  }

  def find(id: Long) = run(people.filter(c => c.id == lift(id))).headOption
  /**
   * List all the people in the database.
   */
  def list(): Seq[Person] = run(people)
}
