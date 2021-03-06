class CreateTasks < ActiveRecord::Migration
  def change
    create_table :tasks do |t|
      t.references :user, index: true, foreign_key: true
      t.datetime :due_date
      t.string :description
      t.boolean :completed

      t.timestamps null: false
    end
    add_index :tasks, [:due_date, :description], unique: true
  end
end
